import React from "react";
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Users from "./Users";
import { NavAdmin } from "./NavAdmin";
import Books from "./Books";

export default function HomeAdmin() {
    return (
        <Router>
            <div>
                <NavAdmin/>
                <Route path={["/users", "/"]} exact component={Users}/>
                <Route path="/books" component={Books}/>
            </div>
        </Router>
    )
}
