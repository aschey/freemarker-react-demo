var useSessionStorage = (
    key,
    initialValue,
    raw
) => {
    const [state, setState] = React.useState(() => {
        const sessionStorageValue = sessionStorage.getItem(key);
        if (typeof sessionStorageValue !== 'string') {
            sessionStorage.setItem(key, raw ? String(initialValue) : JSON.stringify(initialValue));
            return initialValue;
        } else {
            return raw ? sessionStorageValue : JSON.parse(sessionStorageValue || 'null');
        }
    });

    React.useEffect(() => {
        const serializedState = raw ? String(state) : JSON.stringify(state);
        sessionStorage.setItem(key, serializedState);
    });

    return [state, setState];
};

