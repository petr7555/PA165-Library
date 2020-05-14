import React, { useEffect } from 'react';
import './App.css';
import 'antd/dist/antd.css';
import Cookies from 'js-cookie';
import HomeAdmin from "./components/admin/HomeAdmin";
import HomeUser from "./components/user/HomeUser";
import { useStores } from "./stores/useStores";
// do not remove
import {unregister} from "./api/fetchInterceptor";

export default function App() {
    const {userStore} = useStores();
    userStore.authorities = Cookies.get('authorities');

    useEffect(() => {
        userStore.fetchFullUserInfo(Cookies.get('username'));
    }, [])

    return (
        <div className="App">
            {userStore.authorities === "ROLE_ADMIN" ? <HomeAdmin/> : <HomeUser/>}
        </div>
    );
}
