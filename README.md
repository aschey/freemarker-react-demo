# FreeMarker React demo

## Embedding React in a FreeMarker page

### Option 1: Using create-react-app
The app scaffolded with create-react-app is located under `src/main/resources/ui`. The strategy here is to keep this folder isolated so it can function like a standalone React app in dev mode, but when you run `npm run build`, it copies the bundled files into `src/main/resources/static/react` so it can be read by FreeMarker. Additionally, the `index.html` file is moved from it's normal location into `src/main/resources/templates/header.html` so it can be included as the header template for any FreeMarker template files. When the HTML page generated by FreeMarker loads, it should function the same as a standalone React app since all the JavaScript is loaded the same way. These changes are all done by modifying React's normal `npm run build` script.

#### Dev and prod builds
This demo uses Spring profiles to choose between using React's hot reloading dev server or the production build. If `spring.profiles.active=dev` is set in `application.properties`, `FreeMarkerConfig.java` will set a FreeMarker variable called `dev`. FreeMarker templates can then check that variable to determine whether to use `header.html` or `devHeader.html`. The dev version of the header loads the final bundled JavaScript file directly from React's dev server. You will need to run `npm run start` in the UI folder before using this option.

### Option 2: Using minimal Node tooling (Babel only)
If the need arises, this can all be done in a more lightweight fashion at the cost of losing all of the Babel and Webpack configuration that create-react-app gives you out of the box. In order to use JSX, the JavaScript files still need to be transpiled to something that the browser can understand, so using Babel or an equivalent transpiler is unavoidable. The code for this version is located in `src/main/resources/altUi`. This works by directly invoking `babel` to transpile the files and copy the result to `src/main/resources/altUi/dist`. To use this version, switch the `include` statement at the top of `index.flth` from `devHeader.html` to `altHeader.html`.

### Pros and Cons of using create-react-app vs minimal tooling
Pros:
1. create-react-app handles all the complex configuration of babel, webpack, jest, and TypeScript
2. Can leverage hot reloading
3. Generated output is more efficient since it's bundled

Cons:
1. Requires more dependencies
2. Needs a bit more customization in order to integrate with an existing site
3. Generates extra files that we'll need to remove

## Communication between React and FreeMarker
In case the need for sending data between React and FreeMarker arises, this demo includes some possible ways to handle this. FreeMarker does not have any client-side functionality and needs to perform any state updates server-side.

### Sending data from React to FreeMarker via hidden input fields
Since FreeMarker doesn't interact with JavaScript, the easiest way to manipulate its client-side state is by directly manipulating HTML elements in our React code. In order to attempt some form of decoupling, we define a JavaScript function in the FreeMarker template called `setSecret` that we can then invoke from React side. This way React doesn't need to know about the specific structure of the HTML. The `setSecret` function manipulates the value of a hidden input field inside the form which will get sent to the server when the form is submitted.

### Sending data from React to FreeMarker via a direct API call
Since FreeMarker's updates are all done through API calls, we can manually trigger the API call using JavaScript and then refresh the page. This is done via the "API Call" button in the React component. Unfortunately this does not work seamlessly because the API call returns a redirect, but API calls initiated by JavaScript cannot automatically follow redirects. Instead the HTML returned by the redirect is handled just like a normal API call and the page itself does not reload. To get around this, we need to call `window.location.reload()` manually.

### Sending data from React to FreeMarker via form submission
As an alternative to the previous approach, we can manipulate the form directly and then trigger the form submission via JavaScript. This way, the redirect is initiated by the form and will be followed automatically. The downside to this is that the form fields are visibly updated before submission which looks odd. In this example, it's triggered by the "Trigger Form" button in React. I don't really see a use case for this, but I'm leaving it here for sake of completeness.

### Sending data from FreeMarker to React via global variables
FreeMarker's variables are only available server-side so there's no way for React to interact with them directly. Instead, we can add a script to the FreeMarker template that assigns the FreeMarker variable to a global JavaScript variable. In this example, we do this with `numRequests`. 

## Persisting React state between page refreshes
Since FreeMarker reloads the page when updating, any React state that we want to persist while the user is on the page will be lost as soon as a page update is triggered. To cirmcumvent this, we can persist React's state in some kind of temporary browser storage such as a session cookie or session storage. In this example, we define a custom hook called `useSessionStorage` that mimics the built-in `useState` API, but persists everything to session storage behind the scenes. As soon as the tab is closed, anything stored in session storage is lost. 

It's worth noting that session cookies and session storage don't have the same scope. Session cookies are scoped to the entire browser and are shared between tabs, while session storage is only scoped to a single tab. By default, Spring uses session cookies to store the user's session ID, so if we need the scope of the backend session data to match React's state, then we should use session cookies instead of session state as implemented here. Spring's session cookie is marked as http only, so it cannot be accessed via JavaScript and we would have to make a separate one.