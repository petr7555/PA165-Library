import React from 'react';
import 'antd/dist/antd.css';
import { Table } from 'antd';

export default function LoansTableForUser(props) {
    const columns = [
        {
            title: 'Title',
            dataIndex: ['book', 'title'],
            key: 'title'
        },
        {
            title: 'Author',
            dataIndex: ['book', 'author'],
            key: 'author',
        },
        {
            title: 'Borrowed at',
            dataIndex: '',
            key: 'borrowedAt',
            render: (record) => (
                <span>{new Date(record.borrowedAt).toDateString()}</span>
            ),
        },
        {
            title: 'Returned at',
            dataIndex: '',
            key: 'returnedAt',
            render: (record) => (
                record.returnedAt ? <span>{new Date(record.returnedAt).toDateString()}</span> :
                    <span>not returned yet</span>
            ),
        },
        {
            title: 'Return condition',
            dataIndex: 'returnCondition',
            key: 'returnCondition',
        },
    ];

    return <Table columns={columns} dataSource={props.loans} {...props.other}/>;
};
