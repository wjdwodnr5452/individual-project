import React from 'react';
import '../../css/Header.css';
import { useNavigate } from "react-router-dom";

function Header() {

    const navigate = useNavigate();
    
    const homePageMove = () => {
        navigate("/"); // 메인페이지로 이동
    }


    return (
        <header className="header">
            <div className="logo" onClick={homePageMove}>
                <img src="/images/eco.png" alt="로고" className="logo-img"/>
                <span className="logo-text">다같이</span>
            </div>
            <div className="auth-links">
                <a href="/login">로그인</a>
                <a href="/signup">회원가입</a>
            </div>
        </header>
    );
}

export default Header;