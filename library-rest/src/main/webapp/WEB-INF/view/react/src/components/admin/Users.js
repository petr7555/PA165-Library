import React, { useEffect, useRef, useState } from 'react';
import 'antd/dist/antd.css';
import { Button, Input, Space, Table, } from 'antd';
import { useObserver } from "mobx-react-lite";
import LoansTable from "../LoansTable";
import { fetchUsers } from "../../api/apiCalls";
import { MehOutlined, SearchOutlined, SmileOutlined } from '@ant-design/icons';
import Highlighter from "react-highlight-words";


export default function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const users = await fetchUsers();
            setUsers(users);
        }
        fetchData();
    }, [])

    const [searchText, setSearchText] = useState('');
    const [searchedColumn, setSearchedColumn] = useState('');

    const searchInput = useRef(null);

    const getColumnSearchProps = (dataIndex, name) => ({
        filterDropdown: ({
                             setSelectedKeys,
                             selectedKeys,
                             confirm,
                             clearFilters
                         }) => (
            <div style={{padding: 8}}>
                <Input
                    ref={searchInput}
                    placeholder={`Search ${name}`}
                    value={selectedKeys[0]}
                    onChange={e =>
                        setSelectedKeys(e.target.value ? [e.target.value] : [])
                    }
                    onPressEnter={() => handleSearch(selectedKeys, confirm, dataIndex)}
                    style={{width: 188, marginBottom: 8, display: "block"}}
                />
                <Space>
                    <Button
                        type="primary"
                        onClick={() => handleSearch(selectedKeys, confirm, dataIndex)}
                        icon={<SearchOutlined/>}
                        size="small"
                        style={{width: 90}}
                    >
                        Search
                    </Button>
                    <Button
                        onClick={() => handleReset(clearFilters)}
                        size="small"
                        style={{width: 90}}
                    >
                        Reset
                    </Button>
                </Space>
            </div>
        ),
        filterIcon: filtered => (
            <SearchOutlined style={{color: filtered ? "#1890ff" : undefined}}/>
        ),
        onFilter: (value, record) =>
            record[dataIndex]
                .toString()
                .toLowerCase()
                .includes(value.toLowerCase()),
        onFilterDropdownVisibleChange: visible => {
            if (visible) {
                setTimeout(() => searchInput.current.focus());
            }
        },
        render: text =>
            searchedColumn === dataIndex ? (
                <Highlighter
                    highlightStyle={{backgroundColor: "#ffc069", padding: 0}}
                    searchWords={[searchText]}
                    autoEscape
                    textToHighlight={text.toString()}
                />
            ) : (
                text
            )
    });

    const handleSearch = (selectedKeys, confirm, dataIndex) => {
        confirm();
        setSearchText(selectedKeys[0]);
        setSearchedColumn(dataIndex);
    };

    const handleReset = clearFilters => {
        clearFilters();
        setSearchText("");
    };

    const columns = [
        {
            title: 'First name',
            dataIndex: 'firstName',
            key: 'firstName',
            ...getColumnSearchProps('firstName', 'first name'),
        },
        {
            title: 'Last name',
            dataIndex: 'lastName',
            key: 'lastName',
            ...getColumnSearchProps('lastName', 'last name'),
        },
        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email',
            ...getColumnSearchProps('email', 'email'),
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
        return <LoansTable loans={getSingleLoansForUser(id)} other={{pagination: false, size: "medium"}}/>
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
