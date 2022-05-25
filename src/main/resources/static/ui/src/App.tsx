import React from 'react';
import logo from './logo.svg';
import './App.css';

declare global {
  function addCar(make: string, model: string): void;
}

function App() {
  return (
    <>
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
        window.location.reload();
        //setLiked(true);
      }}>
        API Call
      </button>
    </>
  );
}

export default App;
