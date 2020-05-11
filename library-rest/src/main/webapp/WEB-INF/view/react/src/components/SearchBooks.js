import React from 'react';
import 'antd/dist/antd.css';
import { Button, Input, Space, Table } from 'antd';
import Highlighter from 'react-highlight-words';
import { SearchOutlined } from '@ant-design/icons';

const dataInit = [
    {
        id: 1,
        title: "Animal Farm",
        author: "George Orwell",
        available: true
    },
    {
        id: 2,
        title: "1984",
        author: "George Orwell",
        available: true
    },
    {
        id: 3,
        title: "Ostře sledované vlaky",
        author: "Bohumil Hrabal",
        available: false
    }
];

export default class SearchBooks extends React.Component {
    state = {
        data: dataInit,
        searchText: '',
        searchedColumn: '',
    };

    getColumnSearchProps = dataIndex => ({
        filterDropdown: ({setSelectedKeys, selectedKeys, confirm, clearFilters}) => (
            <div style={{padding: 8}}>
                <Input
                    ref={node => {
                        this.searchInput = node;
                    }}
                    placeholder={`Search ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                    onPressEnter={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
                    style={{width: 188, marginBottom: 8, display: 'block'}}
                />
                <Space>
                    <Button
                        type="primary"
                        onClick={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
                        icon={<SearchOutlined/>}
                        size="small"
                        style={{width: 90}}
                    >
                        Search
                    </Button>
                    <Button onClick={() => this.handleReset(clearFilters)} size="small" style={{width: 90}}>
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
            this.state.searchedColumn === dataIndex ? (
                <Highlighter
                    highlightStyle={{backgroundColor: '#ffc069', padding: 0}}
                    searchWords={[this.state.searchText]}
                    autoEscape
                    textToHighlight={text.toString()}
                />
            ) : (
                text
            ),
    });

    handleSearch = (selectedKeys, confirm, dataIndex) => {
        confirm();
        this.setState({
            searchText: selectedKeys[0],
            searchedColumn: dataIndex,
        });
    };

    handleReset = clearFilters => {
        clearFilters();
        this.setState({searchText: ''});
    };

    handleAddToCart = (record) => {
        console.log(record.id);
        const newData =  this.state.data;
        newData.forEach(book => {
            if (book.id === record.id) {
                book.available = false;
            }
        })
        this.setState({
            data: newData
        });
        // console.log(data);
    }

    render() {
        const columns = [
            {
                title: 'Title',
                dataIndex: 'title',
                key: 'title',
                width: '40%',
                ...this.getColumnSearchProps('title'),
            },
            {
                title: 'Author',
                dataIndex: 'author',
                key: 'author',
                width: '40%',
                ...this.getColumnSearchProps('author'),
            },
            {
                title: 'Action',
                dataIndex: '',
                key: 'x',
                render: (record) => (
                    record.available ? <Button onClick={()=>this.handleAddToCart(record)}>Add to cart</Button> : <Button disabled>Not available</Button>
                ),
            },
        ];
        return (
            <div className="table">
                <Table columns={columns} dataSource={this.state.data}/>
            </div>
        );
    }
}
