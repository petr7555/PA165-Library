import React from "react";
import {Nav} from "./Nav";
import {BrowserRouter as Router, Route} from 'react-router-dom';
import MyLoans from "./MyLoans";
import SearchBooks from "./SearchBooks";
import Cart from "./Cart";

export default function HomeUser() {
    return (
        <Router>
            <div>
                <Nav/>
                <Route path={["/search-books", "/"]} exact component={SearchBooks}/>
                <Route path="/my-loans" component={MyLoans}/>
                <Route path="/cart" component={Cart}/>
            </div>
        </Router>
    )
}
