import React from "react";
import "../../css/Header.css";
import { useNavigate } from "react-router-dom";
//import { useAuth } from "../../../components/AuthProvider"; // AuthContext 사용

function Header() {
    //const { isLoggedIn, user } = useAuth();
    const navigate = useNavigate();

    const homePageMove = () => {
        navigate("/");
    };

    const myPageMove = () => {
        navigate("/mypage");
    };

    const logout = async () => {
        // 로그아웃 처리
        const response = await fetch(`${process.env.REACT_APP_API_URL}/api/logout`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include",
        });

        if (response.ok) {
            window.location.reload(); // 전체 새로고침으로 상태 초기화
        } else {
            alert("로그아웃 실패");
        }
    };

    return (
        <header className="header">
            <div className="logo" onClick={homePageMove}>
                <img src="/images/eco.png" alt="로고" className="logo-img" />
                <span className="logo-text">다같이</span>
            </div>
            <div className="auth-links">
                <a href="/login">로그인</a>
                <a href="/signup">회원가입</a>
                {/*          {isLoggedIn ? (
                    <>
                        <span>{user?.name}님</span>
                        <a href="#" onClick={myPageMove}>마이페이지</a>
                        <a href="#" onClick={logout}>로그아웃</a>
                    </>
                ) : (
                    <>
                        <a href="/login">로그인</a>
                        <a href="/signup">회원가입</a>
                    </>
                )}*/}
            </div>
        </header>
    );
}

export default Header;
