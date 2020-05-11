import React from "react";
import {Nav} from "./Nav";
import {BrowserRouter as Router, Route} from 'react-router-dom';
import MyLoans from "./MyLoans";
import SearchBooks from "./SearchBooks";

export default function HomeUser() {
    return (
        <Router>
            <div>
                <Nav/>
                <Route path={["/search-books", "/"]} exact component={SearchBooks}/>
                <Route path="/my-loans" component={MyLoans}/>
            </div>
        </Router>
    )
}
