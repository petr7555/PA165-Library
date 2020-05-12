import React from "react";
import { Menu } from "antd";
import { BookOutlined, TeamOutlined } from '@ant-design/icons';
import { Link, withRouter } from 'react-router-dom';
import UserIcon from "../UserIcon";

export const NavAdmin = withRouter((props) => {
    const {location} = props;

    return (
            <div>
                <Menu selectedKeys={[location.pathname === "/" ? "/users" : location.pathname]}
                      mode="horizontal">
                    <Menu.Item key="/users">
                        <Link to="/users">
                            <TeamOutlined/>
                            <span>Users</span>
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="/books">
                        <Link to="/books">
                            <BookOutlined/>
                            <span>Books</span>
                        </Link>
                    </Menu.Item>
                    <Menu.Item>
                        <UserIcon/>
                    </Menu.Item>
                </Menu>
            </div>
    );
})
