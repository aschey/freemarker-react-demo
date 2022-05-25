import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { useSessionStorage } from './useSessionStorage';

declare global {
  function addCar(make: string, model: string): void;
  function setSecret(secret: string): void;
  interface Window {
    numRequests: number;
  }
}

const App: React.FC<{}> = () => {
  const [checked, setChecked] = useSessionStorage('checked', false);
  const [secretVal, setSecretVal] = useSessionStorage('secret', '');

  useEffect(() => {
    setSecret(secretVal);
  }, [secretVal]);

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
        formData.append('secret', secretVal);
        await fetch('add', {
          method: 'POST',
          body: formData,
          // This causes a request cancelled error in the browser but it prevents the useless call to the redirect uri
          redirect: 'manual',
        })
        // Javascript-initiated requests can't automatically follow redirects. The response is just treated as a normal api response and no redirect is performed.
        window.location.reload();
      }}>
        API Call
      </button>
      <input type='checkbox' defaultChecked={checked} onClick={() => setChecked(checked => !checked)} />
      <input type='text' value={secretVal} onChange={(e) => setSecretVal(e.currentTarget.value)} />
      Num Requests: {window.numRequests}
    </>
  );
}

export default App;
