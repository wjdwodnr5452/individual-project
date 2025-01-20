import React, {useEffect,useState } from 'react';
import '../../css/user/Signup.css';
import {useNavigate} from "react-router-dom";

const Signup = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: '',
        confirmPassword: '',
        phoneNumber: ''
    });

    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;

        // 이메일 정규식
        if(!emailRegex.test(formData.email)) {
            alert('이메일 형식에 따라 정확히 입력해주세요');
            return;
        }

        // 비밀번호 정규식
        if(!passwordRegex.test(formData.password)){
            alert('하나이상 대소문자, 특수문자를 넣어주시기 바랍니다. 비밀번호 최소 길이는 8자 이상입니다.');
            return;
        }

        // 비밀번호 확인 유효성 검사
        if (formData.password !== formData.confirmPassword) {
            setError('비밀번호가 일치하지 않습니다.');
            alert(error);
            return;
        }

        try {
            // 서버요청
            const response = await fetch(`${process.env.REACT_APP_API_URL}/api/users`,{
                method : "POST",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({
                    email : formData.email,
                    password : formData.password,
                    name : formData.name,
                    phoneNumber : formData.phoneNumber
                })
            });

            if (response.ok) {
                // 서버 응답 데이터를 JSON으로 변환
                const data = await response.json();
                setSuccess(data.msg);
                alert(data.msg);

                navigate("/login"); // 회원가입 완료 후 로그인 페이지로 이동
            } else {
                // 서버 응답이 실패한 경우 에러 처리
                const errorData = await response.json();
                setError(errorData.msg || "회원가입에 실패했습니다.");
                alert(setError);
            }

        }catch (err){
            setError("서버와 통신하는 중 오류가 발생했습니다.");
            alert("서버와 통신하는 중 오류가 발생했습니다.");
        }

    };

    return (
        <div className="signup-container">
            <h2>회원가입</h2>
            <form onSubmit={handleSubmit} className="signup-form">
                <div className="form-group">
                    <label htmlFor="name">사용자 이름</label>
                    <input type="text" id="name" name="name" value={formData.username} onChange={handleChange}
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
                    <label htmlFor="phoneNumber">핸드폰 번호</label>
                    <input type="text" id="phoneNumber" name="phoneNumber" value={formData.username} onChange={handleChange}  required/>
                </div>
                <button type="submit" className="submit-btn">
                    회원가입
                </button>
            </form>
        </div>
    );
};

export default Signup;
