import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { useSessionStorage } from './useSessionStorage';

declare global {
  function addCar(make: string, model: string): void;
}

const App: React.FC<{}> = () => {
  const [checked, setChecked] = useSessionStorage("checked", false);

  return (
    <>
      <button onClick={async () => {
        addCar('Bugatti', 'Veyron');
      }}>
        Trigger Form
      </button>
      <button onClick={async () => {
        let formData = new FormData();
        formData.append('make', 'Ferrari');
        formData.append('model', 'Enzo');
        await fetch('add', {
          method: 'POST',
          body: formData,
          // This causes a request cancelled error in the browser but it prevents the useless call to the redirect uri
          redirect: 'manual',
        })
        // Javascript-initiated requests can't automatically follow redirects. The response is just treated as a normal api response and no redirect is performed.
        window.location.reload();
        //setLiked(true);
      }}>
        API Call
      </button>
      <input type={'checkbox'} checked={checked} onClick={() => setChecked(checked => !checked)} />
    </>
  );
}

export default App;
