import React from "react";
import { NavUser } from "./NavUser";
import { BrowserRouter as Router, Route } from 'react-router-dom';
import MyLoans from "./MyLoans";
import SearchBooks from "./SearchBooks";
import Cart from "./Cart";

export default function HomeUser() {
    return (
        <Router>
            <div>
                <NavUser/>
                <Route path={["/search_books", "/"]} exact component={SearchBooks}/>
                <Route path="/my_loans" component={MyLoans}/>
                <Route path="/cart" component={Cart}/>
            </div>
        </Router>
    )
}
