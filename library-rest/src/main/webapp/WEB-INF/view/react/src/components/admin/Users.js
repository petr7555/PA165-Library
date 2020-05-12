import React, { useEffect, useState } from 'react';
import 'antd/dist/antd.css';
import { Button, Table, } from 'antd';
import { useObserver } from "mobx-react-lite";
import LoansTable from "../LoansTable";
import { fetchUsers } from "../../api/apiCalls";
import { MehOutlined, SmileOutlined } from '@ant-design/icons';


export default function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const users = await fetchUsers();
            setUsers(users);
        }
        fetchData();
    }, [])

    const columns = [
        {
            title: 'First name',
            dataIndex: 'firstName',
            key: 'firstName',
        },
        {
            title: 'Last name',
            dataIndex: 'lastName',
            key: 'lastName',
        },
        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email',
        },
        {
            title: 'Action',
            dataIndex: '',
            key: 'x',
            render: (record) => (
                <Button onClick={() => handleRowExpand(record)}
                        disabled={getSingleLoansForUser(record.id).length === 0}>Show loans</Button>
            ),
        },
    ];

    const [expandedRows, setExpandedRows] = useState([]);

    const handleRowExpand = (record) => {
        setExpandedRows(expandedRows.includes(record.key) ?
            expandedRows.filter(key => key !== record.key) :
            [...expandedRows, record.key]);
    }

    const getSingleLoansForUser = (id) => {
        return users.find(user => user.id === id).singleLoans;
    }

    const expandRow = (id) => {
        return <LoansTable loans={getSingleLoansForUser(id)}/>
    }

    const customExpandIcon = (props) => {
        const iconStyle = {fontSize: '24px', color: '#08c'};
        if (props.expanded) {
            return <SmileOutlined style={iconStyle}/>
        } else {
            return <MehOutlined style={iconStyle}/>
        }
    }

    return useObserver(() => (
        <div className="table">
            <Table
                columns={columns}
                dataSource={users}
                expandable={{
                    expandIcon: (props) => customExpandIcon(props),
                    onExpand: (expanded, record) => handleRowExpand(record),
                    expandedRowKeys: expandedRows,
                    expandedRowRender: record => expandRow(record.id)
                }}
            />
        </div>
    ));
};
