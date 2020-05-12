import React, { useEffect } from 'react';
import 'antd/dist/antd.css';
import { Button, Table } from 'antd';
import { useStores } from "../../stores/useStores";
import { useObserver } from "mobx-react-lite";

export default function Books() {
    let {userStore} = useStores();

    useEffect(()=>{
        userStore.fetchBooks();
    },[])

    const columns = [
        {
            title: 'Title',
            dataIndex: 'title',
            key: 'title'
        },
        {
            title: 'Author',
            dataIndex: 'author',
            key: 'author',
        },
        {
            title: 'Action',
            dataIndex: '',
            key: 'x',
            render: (record) => (
                <Button>Show history</Button>
            ),
        },
    ];

    return useObserver(() => (
        <div className="table">
            <Table columns={columns} dataSource={userStore.books}/>
        </div>
    ));
};
