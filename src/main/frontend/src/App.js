import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AuthProvider } from "./components/AuthProvider";
import HomePage from './pages/js/HomePage';
import LoginPage from "./pages/js/user/LoginPage";
import Signup from "./pages/js/user/Signup";
import BoardDetail from "./pages/js/board/BoardDetail";
import BoardWrite from "./pages/js/board/BoardWrite";
import BoardEdit from "./pages/js/board/BoardEdit";
import UserProfile from "./pages/js/user/UserProfile";
import Header from "./pages/js/layout/Header";
import Footer from "./pages/js/layout/Footer";
import Notifications from "./components/Notifications";

function App() {
    return (
        <AuthProvider>
            <Router>
                <div>
                    <Header/>
                    <Notifications enabled={true} />
                        <Routes>
                                <Route path="/" element={<HomePage />} />  {/* 메인페이지 */}
                                <Route path="/boards/write" element={<BoardWrite />} /> {/* 게시판 작성페이지 */}
                                <Route path="/boards/:id" element={<BoardDetail />} /> {/* 게시판 상세 페이지 */}
                                <Route path="/boards/:id/edit" element={<BoardEdit />} /> {/* 게시판 상세 페이지 */}
                                <Route path="/users/:id" element={<UserProfile />} /> {/* 회원정보 페이지 */}
                            <Route path="/login" element={<LoginPage />} /> {/* 로그인페이지 */}
                            <Route path="/signup" element={<Signup />} /> {/* 회원가입 */}
                        </Routes>
                    <Footer/>
                </div>
            </Router>
        </AuthProvider>
    );
}

export default App;