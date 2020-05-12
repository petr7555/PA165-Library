import React, { useEffect } from 'react';
import 'antd/dist/antd.css';
import { useStores } from "../../stores/useStores";
import { useObserver } from "mobx-react-lite";
import LoansTableForUser from "../LoansTableForUser";

export default function MyLoans() {
    let {userStore} = useStores();

    useEffect(() => {
        userStore.fetchMyLoans();
    }, [])

    return useObserver(() =>
        <div className="table">
            <LoansTableForUser loans={userStore.myLoans}/>
        </div>
    );
};
