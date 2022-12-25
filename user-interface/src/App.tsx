import React from 'react';
import {BrowserRouter as Router, Route, Routes,} from "react-router-dom";
import {SignIn} from "./pages/SignIn";
import {Users} from "./pages/Users";
import {Registration} from "./pages/Registration";

function App() {
    return (
        <Router>
                <Routes>
                    <Route path="/signin" element={<SignIn/>} />
                    <Route path="/users" element={<Users/>} />
                    <Route path="/signup" element={<Registration/>} />
                </Routes>
        </Router>
    )
}

export default App;
