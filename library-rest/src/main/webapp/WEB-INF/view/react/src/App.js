import React from 'react';
import './App.css';
import 'antd/dist/antd.css';
import Cookies from 'js-cookie';
import HomeAdmin from "./components/HomeAdmin";
import HomeUser from "./components/HomeUser";
import { useStores } from "./stores/useStores";

export default function App() {
    const {userStore} = useStores();
    userStore.username = Cookies.get('username');
    userStore.authorities = Cookies.get('authorities');

    return (
        <div className="App">
                {userStore.authorities === "ROLE_ADMIN" ? <HomeAdmin/> : <HomeUser/>}
        </div>
    );
}
