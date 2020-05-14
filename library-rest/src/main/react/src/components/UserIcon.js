import { Avatar } from "antd";
import { UserOutlined } from "@ant-design/icons";
import React from "react";
import { useStores } from "../stores/useStores";
import { useObserver } from "mobx-react-lite";

export default function UserIcon() {
    const {userStore} = useStores()

    return useObserver(() =>
        <>
            <Avatar shape="square" icon={<UserOutlined/>}/>
            <span className="user-email">{userStore.user.email}</span>
            <a href="http://localhost:8080/logout"/>
        </>
    );
}
