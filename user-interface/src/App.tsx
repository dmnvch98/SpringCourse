import React from 'react';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {Allusers} from "./pages/Allusers";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/allusers" element={<Allusers/>} />
            </Routes>
        </Router>
    )
}

export default App;
