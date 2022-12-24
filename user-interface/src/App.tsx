import React from 'react';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {Allusers} from "./pages/Allusers";
import {SignUp} from "./pages/SignUp";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/allusers" element={<Allusers/>} />
                <Route path="/signup" element={<SignUp/>} />
            </Routes>
        </Router>
    )
}

export default App;
