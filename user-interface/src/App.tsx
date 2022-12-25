import React from 'react';
import {BrowserRouter as Router, Route, Routes,} from "react-router-dom";
import {Allusers} from "./pages/Allusers";
import {SignUp} from "./pages/SignUp";
import {SignIn} from "./pages/SignIn";
import {useSelector} from "react-redux";

function App() {
    //const isAuth = useSelector(state => state.user.isAuth)

    return (
        <Router>
                <Routes>
                    <Route path="/signup" element={<SignUp/>} />
                    <Route path="/signin" element={<SignIn/>} />
                    <Route path="/allusers" element={<Allusers/>} />
                </Routes>
        </Router>
    )
}

export default App;
