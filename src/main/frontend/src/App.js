import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/js/HomePage';
import LoginPage from "./pages/js/user/LoginPage";
import Signup from "./pages/js/user/Signup";
import BoardDetail from "./pages/js/board/BoardDetail";


function App() {
    return (
        <Router>
            <div>
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/signup" element={<Signup />} />
                    <Route path="/boards/:id" element={<BoardDetail />} />
               {/*     <Route path="/boards" element={<BoardDetail />} />*/}
                </Routes>
            </div>
        </Router>
    );
}

export default App;