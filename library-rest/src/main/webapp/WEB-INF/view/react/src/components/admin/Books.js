import React, { useEffect, useRef, useState } from 'react';
import 'antd/dist/antd.css';
import { Button, Input, Space, Table, } from 'antd';
import { useObserver } from "mobx-react-lite";
import LoansTable from "../LoansTable";
import { fetchBooks } from "../../api/apiCalls";
import { MehOutlined, SearchOutlined, SmileOutlined } from '@ant-design/icons';
import Highlighter from "react-highlight-words";

export default function Books() {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const books = await fetchBooks();
            setBooks(books);
        }
        fetchData();
    }, [])

    const [searchText, setSearchText] = useState('');
    const [searchedColumn, setSearchedColumn] = useState('');

    const searchInput = useRef(null);

    const getColumnSearchProps = dataIndex => ({
        filterDropdown: ({
                             setSelectedKeys,
                             selectedKeys,
                             confirm,
                             clearFilters
                         }) => (
            <div style={{padding: 8}}>
                <Input
                    ref={searchInput}
                    placeholder={`Search ${dataIndex}`}
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
            title: 'Title',
            dataIndex: 'title',
            key: 'title',
            ...getColumnSearchProps('title'),
        },
        {
            title: 'Author',
            dataIndex: 'author',
            key: 'author',
            ...getColumnSearchProps('author'),
        },
        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email',
            ...getColumnSearchProps('email'),
        },
        {
            title: '',
            dataIndex: '',
            key: 'delete',
            render: (record) => (
                <Button onClick={() => handleRowExpand(record)}>Delete</Button>
            ),
        },
        {
            title: '',
            dataIndex: '',
            key: 'showHistory',
            render: (record) => (
                <Button onClick={() => handleRowExpand(record)}
                        disabled={getSingleLoansForBook(record.id).length === 0}>Show history</Button>
            ),
        },
    ];

    const [expandedRows, setExpandedRows] = useState([]);

    const handleRowExpand = (record) => {
        setExpandedRows(expandedRows.includes(record.key) ?
            expandedRows.filter(key => key !== record.key) :
            [...expandedRows, record.key]);
    }

    const getSingleLoansForBook = (id) => {
        return books.find(book => book.id === id).singleLoans;
    }

    const expandRow = (id) => {
        return <LoansTable loans={getSingleLoansForBook(id)} other={{pagination: false, size: "medium"}}/>
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
                dataSource={books}
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
