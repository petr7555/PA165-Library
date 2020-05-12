import React, { useEffect, useState } from 'react';
import 'antd/dist/antd.css';
import { Button, Input, Space, Table } from 'antd';
import Highlighter from 'react-highlight-words';
import { SearchOutlined } from '@ant-design/icons';
import { useStores } from "../../stores/useStores";
import { observer, useObserver } from "mobx-react-lite";

export default function SearchBooks() {
    const [searchText, setSearchText] = useState('');
    const [searchedColumn, setSearchedColumn] = useState('');

    let {userStore} = useStores();

    useEffect(()=>{
        userStore.fetchBooks();
    },[])

    const getColumnSearchProps = dataIndex => ({
        filterDropdown: ({setSelectedKeys, selectedKeys, confirm, clearFilters}) => (
            <div style={{padding: 8}}>
                <Input
                    ref={node => {
                        this.searchInput = node;
                    }}
                    placeholder={`Search ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                    onPressEnter={() => handleSearch(selectedKeys, confirm, dataIndex)}
                    style={{width: 188, marginBottom: 8, display: 'block'}}
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
                    <Button onClick={() => handleReset(clearFilters)} size="small" style={{width: 90}}>
                        Reset
                    </Button>
                </Space>
            </div>
        ),
        filterIcon: filtered => <SearchOutlined style={{color: filtered ? '#1890ff' : undefined}}/>,
        onFilter: (value, record) =>
            record[dataIndex].toString().toLowerCase().includes(value.toLowerCase()),
        onFilterDropdownVisibleChange: visible => {
            if (visible) {
                setTimeout(() => this.searchInput.select());
            }
        },
        render: text =>
            searchedColumn === dataIndex ? (
                <Highlighter
                    highlightStyle={{backgroundColor: '#ffc069', padding: 0}}
                    searchWords={[searchText]}
                    autoEscape
                    textToHighlight={text.toString()}
                />
            ) : (
                text
            ),
    });

    const handleSearch = (selectedKeys, confirm, dataIndex) => {
        confirm();
        setSearchText(selectedKeys[0]);
        setSearchedColumn(dataIndex);
    };

    const handleReset = clearFilters => {
        clearFilters();
        setSearchText('');
    };

    const handleAddToCart = (book) => {
        userStore.addToCart(book);
    }

    const columns = [
        {
            title: 'Title',
            dataIndex: 'title',
            key: 'title',
            width: '40%',
            ...getColumnSearchProps('title'),
        },
        {
            title: 'Author',
            dataIndex: 'author',
            key: 'author',
            width: '40%',
            ...getColumnSearchProps('author'),
        },
        {
            title: 'Action',
            dataIndex: '',
            key: 'x',
            render: (record) => (
                record.available ? <Button onClick={()=>handleAddToCart(record)}>Add to cart</Button> :
                    <Button disabled>Not available</Button>
            ),
        },
    ];

    return useObserver(() => (
        <div className="table">
            <Table columns={columns} dataSource={userStore.books}/>
        </div>
    ));
};
