import React, { useEffect, useRef, useState } from 'react';
import 'antd/dist/antd.css';
import { Button, Input, message, Popconfirm, Space, Table } from 'antd';
import { useObserver } from "mobx-react-lite";
import { createBook, deleteBook, fetchBooks } from "../../api/apiCalls";
import { DeleteOutlined, FormOutlined, MehOutlined, SearchOutlined, SmileOutlined } from '@ant-design/icons';
import Highlighter from "react-highlight-words";
import LoansTableForBook from "./LoansTableForBook";

const EditableCell = () => {
    return <td>table data</td>
}

export default function Books() {
    const [books, setBooks] = useState([]);

    const fetchData = async () => {
        const books = await fetchBooks();
        setBooks(books);
    }

    useEffect(() => {
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

    function confirm(book) {
        deleteBook(book).then(() => fetchData());

    }

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
            title: '',
            dataIndex: '',
            key: 'delete',
            render: (record) => {
                if (record.id === -1) {
                    return null;
                } else {
                    return (
                        <Popconfirm placement="bottom" title="Are you sure to delete this book?" onConfirm={() => {
                            confirm(record);
                        }}
                                    okText="Yes" cancelText="No">
                            <a href="#"><DeleteOutlined/></a>
                        </Popconfirm>);
                }
            }
        },
        {
            title: '',
            dataIndex: '',
            key: 'showHistory',
            render: (record) => {
                if (record.id === -1) {
                    return <Button type="primary" onClick={() => handleAdd(record)}>Add book</Button>
                } else {
                    return <Button onClick={() => handleRowExpand(record)}
                                   disabled={getSingleLoansForBook(record.id).length === 0}>Show history</Button>
                }
            },
        },
    ];

    const handleAdd = (book) => {
        if (book.id != null && book.title != null) {
            createBook(book);
        } else {
            message.warning("All fields must be set.")
        }
    }

    const [expandedRows, setExpandedRows] = useState([]);

    const handleRowExpand = (record) => {
        setExpandedRows(expandedRows.includes(record.key) ?
            expandedRows.filter(key => key !== record.key) :
            [...expandedRows, record.key]);
    }

    const getSingleLoansForBook = (id) => {
        return books.find(book => book.id === id).singleLoans || [];
    }

    const expandRow = (id) => {
        return <LoansTableForBook loans={getSingleLoansForBook(id)} other={{pagination: false, size: "medium"}}/>
    }

    const customExpandIcon = (props) => {
        const iconStyle = {fontSize: '24px', color: '#08c'};
        if (props.record.id === -1) {
            return <FormOutlined style={iconStyle}/>
        } else if (props.expanded) {
            return <SmileOutlined style={iconStyle}/>
        } else {
            return <MehOutlined style={iconStyle}/>
        }
    }

    const components = {
        body: {
            cell: EditableCell,
        },
    };

    return useObserver(() => (
        <div className="table">
            <Table
                components={components}
                columns={columns}
                dataSource={[...books, {id: -1}]}
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
