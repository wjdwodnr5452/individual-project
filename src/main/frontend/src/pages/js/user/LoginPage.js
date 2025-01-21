// LoginPage.js
import React, { useState } from "react";
import "../../css/user/Login.css";
import {useNavigate} from "react-router-dom";

const LoginPage = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        try{
            // 서버요청
            const response = await fetch(`${process.env.REACT_APP_API_URL}/api/login`,{
                method : "POST",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({
                    email : email,
                    password : password
                }),
                credentials: 'include' // 요청한 쿠키 값 저장하기
            });

            if(response.ok){
                const responseData = await response.json();
                console.log("data : " , responseData.data);

                // 로그인 성공 시, 사용자 정보를 localStorage에 저장
                localStorage.setItem("user", JSON.stringify(responseData.data));

                // 로그인 성공 후, 메인 페이지로 이동
                navigate("/");

            }

        }catch (err){

        }
    };

    return (
        <div className="login-page">
            <h2>로그인</h2>
            <form onSubmit={handleSubmit} className="login-form">
                <div className="form-group">
                    <label htmlFor="email">이메일</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="이메일을 입력하세요"
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="password">비밀번호</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="비밀번호를 입력하세요"
                        required
                    />
                </div>

                {error && <p className="error-message">{error}</p>}

                <button type="submit" className="login-button">
                    로그인
                </button>
            </form>
        </div>
    );
};

export default LoginPage;
