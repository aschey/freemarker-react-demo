'use strict';

const e = React.createElement;
const useState = React.useState;

function LikeButton() {
    const [liked, setLiked] = useState(false);

    return (
        <React.Fragment>
            <button onClick={async () => {
                addCar('yes', 'yes');
            }}>
                Trigger Form
            </button>
            <button onClick={async () => {
                // addCar('yes', 'yes');
                let formData = new FormData();
                formData.append('make', 'Ferrari');
                formData.append('model', 'Enzo');
                let res = await fetch('add', {
                    method: 'POST',
                    body: formData,
                    // This causes a request cancelled error in the browser but it prevents the useless call to the redirect uri
                    redirect: 'manual',
                })

                console.log(res.redirected);
                // Javascript-initiated requests can't automatically follow redirects. The response is just treated as a normal api response and no redirect is performed.
                location.reload();
                //setLiked(true);
            }}>
                API Call
            </button>
        </React.Fragment>);
}

const domContainer = document.querySelector('#like_button_container');
const root = ReactDOM.createRoot(domContainer);
root.render(<LikeButton />);
