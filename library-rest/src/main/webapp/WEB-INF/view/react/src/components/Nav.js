import React from "react";
import { Avatar, Menu } from "antd";
import { BookOutlined, FileOutlined, ShoppingCartOutlined, UserOutlined } from '@ant-design/icons';
import { Link, withRouter } from 'react-router-dom';
import { useStores } from "../stores/useStores";
import { Observer } from 'mobx-react-lite'

export const Nav = withRouter((props) => {
    const {location} = props;
    const {userStore} = useStores();

    return (
        <Observer>{()=>(
            <div>
                <Menu selectedKeys={[location.pathname === "/" ? "/search-books" : location.pathname]}
                      mode="horizontal">
                    <Menu.Item key="/search-books">
                        <Link to="/search-books">
                            <BookOutlined/>
                            <span>Search books</span>
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="/my-loans">
                        <Link to="/my-loans">
                            <FileOutlined/>
                            <span>My loans</span>
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="/cart">
                        <Link to="/cart">
                            <ShoppingCartOutlined/>
                            <span>You have {userStore.booksInCart.length} {userStore.booksInCart.length === 1 ? "item" : "items"} in your cart</span>
                        </Link>
                    </Menu.Item>
                    <Menu.Item disabled>
                        <Avatar shape="square" icon={<UserOutlined/>}/>
                        <span className="user-email">{userStore.user.email}</span>
                    </Menu.Item>
                </Menu>
            </div>
            )}
        </Observer>
    );
})
