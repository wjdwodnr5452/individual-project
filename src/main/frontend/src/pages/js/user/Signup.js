import React, { useState } from 'react';
import '../../css/user/Signup.css'; // 스타일 파일

const Signup = () => {
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // 유효성 검사 및 회원가입 처리
        console.log(formData);
    };

    return (
        <div className="signup-container">
            <h2>회원가입</h2>
            <form onSubmit={handleSubmit} className="signup-form">
                <div className="form-group">
                    <label htmlFor="username">사용자 이름</label>
                    <input type="text" id="username" name="username" value={formData.username} onChange={handleChange}
                           required/>
                </div>
                <div className="form-group">
                    <label htmlFor="email">이메일</label>
                    <input type="email" id="email" name="email" value={formData.email} onChange={handleChange}
                           required/>
                </div>
                <div className="form-group">
                    <label htmlFor="password">비밀번호</label>
                    <input type="password" id="password" name="password" value={formData.password}
                           onChange={handleChange} required/>
                </div>
                <div className="form-group">
                    <label htmlFor="confirmPassword">비밀번호 확인</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" value={formData.confirmPassword}
                           onChange={handleChange} required/>
                </div>
                <div className="form-group">
                    <label htmlFor="phonnum">핸드폰 번호</label>
                    <input type="text" id="phonnum" name="phonnum" value={formData.username} onChange={handleChange}
                           required/>
                </div>
                <button type="submit" className="submit-btn">
                    회원가입
                </button>
            </form>
        </div>
    );
};

export default Signup;
