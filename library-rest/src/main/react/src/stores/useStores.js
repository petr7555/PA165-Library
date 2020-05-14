import React from 'react'
import { useLocalStore } from 'mobx-react-lite'
import { createUserStore } from "./createUserStore";

const storeContext = React.createContext(null);

export const StoreProvider = ({children}) => {
    const stores = {
        userStore: useLocalStore(createUserStore),
    }
    return <storeContext.Provider value={stores}>{children}</storeContext.Provider>
}

export const useStores = () => {
    const store = React.useContext(storeContext);
    if (!store) {
        // this is especially useful in TypeScript so you don't need to be checking for null all the time
        throw new Error('useStore must be used within a StoreProvider.');
    }
    return store;
}
