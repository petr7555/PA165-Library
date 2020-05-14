import React, { useEffect } from 'react';
import './App.css';
import 'antd/dist/antd.css';
import Cookies from 'js-cookie';
import HomeAdmin from "./components/admin/HomeAdmin";
import HomeUser from "./components/user/HomeUser";
import { useStores } from "./stores/useStores";
// do not remove

export default function App() {
    const {userStore} = useStores();
    userStore.authorities = (Cookies.get('authorities') && Cookies.get('authorities').split('-')) || [];

    useEffect(() => {
        userStore.fetchFullUserInfo(Cookies.get('username'));
    }, [])

    return (
        <div className="App">
            {userStore.authorities.includes('ROLE_ADMIN') ? <HomeAdmin/> : <HomeUser/>}
        </div>
    );
}
