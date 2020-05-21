import React from 'react';
import 'antd/dist/antd.css';
import { Button, Table } from 'antd';
import { useStores } from "../../stores/useStores";
import { useObserver } from "mobx-react-lite";

export default function Cart() {
    let {userStore} = useStores();

    const handleRemoveFromCart = (book) => {
        userStore.removeFromCart(book);
    }

    const handleSubmit = () => {
        userStore.submitRequest();
    }

    const columns = [
        {
            title: 'Title',
            dataIndex: 'title',
            key: 'title',
            width: '40%',
        },
        {
            title: 'Author',
            dataIndex: 'author',
            key: 'author',
            width: '40%',
        },
        {
            title: 'Action',
            dataIndex: '',
            key: 'remove',
            render: (record) => (
                <Button onClick={() => handleRemoveFromCart(record)}>Remove</Button>
            ),
        },
    ];

    return useObserver(() => (
        <div className="table">
            <Table columns={columns} dataSource={userStore.booksInCart} emptyText='No Books'/>
            <Button className="button-submit-loan-request" disabled={userStore.booksInCart.length === 0}
                    onClick={() => handleSubmit()} type="primary">Submit loan request</Button>
        </div>
    ));
};
