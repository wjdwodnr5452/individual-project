import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/js/HomePage';
import LoginPage from "./pages/js/user/LoginPage";
import Signup from "./pages/js/user/Signup";
import BoardDetail from "./pages/js/board/BoardDetail";
import BoardWrite from "./pages/js/board/BoardWrite";


function App() {
    return (
        <Router>
            <div>
                
                
                <Routes>
                    <Route path="/" element={<HomePage />} />  {/* 메인페이지 */}
                    <Route path="/login" element={<LoginPage />} /> {/* 로그인페이지 */}
                    <Route path="/signup" element={<Signup />} /> {/* 회원가입 */}
                    <Route path="/boards/create" element={<BoardWrite />} /> {/* 게시판 상세 페이지 */}
                    <Route path="/boards/:id" element={<BoardDetail />} /> {/* 게시판 상세 페이지 */}
                </Routes>
            </div>
        </Router>
    );
}

export default App;