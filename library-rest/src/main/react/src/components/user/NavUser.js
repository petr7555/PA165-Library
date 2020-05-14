import React from "react";
import { Menu } from "antd";
import { BookOutlined, FileOutlined, ShoppingCartOutlined } from '@ant-design/icons';
import { Link, withRouter } from 'react-router-dom';
import { useStores } from "../../stores/useStores";
import { Observer } from 'mobx-react-lite'
import UserIcon from "../UserIcon";

export const NavUser = withRouter((props) => {
    const {location} = props;
    const {userStore} = useStores();

    return (
        <Observer>{() => (
            <div>
                <Menu selectedKeys={[location.pathname === "/" ? "/search_books" : location.pathname]}
                      mode="horizontal">
                    <Menu.Item key="/search_books">
                        <Link to="/search_books">
                            <BookOutlined/>
                            <span>Search books</span>
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="/my_loans">
                        <Link to="/my_loans">
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
                    <Menu.Item>
                        <UserIcon/>
                    </Menu.Item>
                </Menu>
            </div>
        )}
        </Observer>
    );
})
