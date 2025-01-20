import React, { useEffect, useState } from 'react';
import '../../css/Header.css';
import { useNavigate } from "react-router-dom";

function Header() {

    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const user = localStorage.getItem("user"); // 로그인한 사용자 정보가 localStorage에 있다고 가정
        if (user) {
            setIsLoggedIn(true);
        }
    }, []);


    const homePageMove = () => {
        navigate("/"); // 메인페이지로 이동
    };

    const myPageMove = () => {
        navigate("/mypage"); // 마이페이지로 이동
    };

    const logout = async () => {
        // 로그아웃 처리
        const response = await fetch(`${process.env.REACT_APP_API_URL}/api/logout`,{
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            credentials: 'include'
        });

        if (response.ok) {
            localStorage.removeItem("user"); // localStorage에서 사용자 정보 삭제
            setIsLoggedIn(false); // 로그인 상태 갱신
            navigate("/"); // 로그아웃 후 홈 페이지로 이동
        }else{
            alert("로그아웃에 실패 했습니다.");
        }
    };


    return (
        <header className="header">
            <div className="logo" onClick={homePageMove}>
                <img src="/images/eco.png" alt="로고" className="logo-img"/>
                <span className="logo-text">다같이</span>
            </div>
            <div className="auth-links">
                {isLoggedIn ? (
                    <>
                        <a href="#" onClick={myPageMove}>마이페이지</a>
                        <a href="#" onClick={logout}>로그아웃</a>
                    </>
                ) : (
                    <>
                        <a href="/login">로그인</a>
                        <a href="/signup">회원가입</a>
                    </>
                )}
            </div>
        </header>
    );
}

export default Header;