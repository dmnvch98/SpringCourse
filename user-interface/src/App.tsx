import React from 'react';
import {BrowserRouter as Router, Route, Routes,} from "react-router-dom";
import {SignIn} from "./pages/SignIn";
import {Users} from "./pages/Users";
import {SignUp} from "./pages/SignUp";

function App() {
    return (
        <Router>
                <Routes>
                    <Route path="/signin" element={<SignIn/>} />
                    <Route path="/users" element={<Users/>} />
                    <Route path="/signup" element={<SignUp/>} />
                </Routes>
        </Router>
    )
}

export default App;
