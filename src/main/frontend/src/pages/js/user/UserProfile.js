import React, { useState } from "react";
import "../../css/user/UserProfile.css";
import Header from "../layout/Header";
import Footer from "../layout/Footer";

const UserProfile = () => {
    const [user, setUser] = useState({
        name: "홍길동",
        email: "hong@example.com",
        phone: "010-1234-5678",
        serviceTime: "30",
    });

    const handleEdit = () => {
        alert("회원정보 수정 페이지로 이동합니다.");
    };

    return (
        <div>
            <Header />
            <div className="user-profile-container">
                <h2 className="profile-title">회원 정보</h2>

                <div className="profile-card">
                    <div className="profile-item">
                        <label>이름:</label>
                        <span>{user.name}</span>
                    </div>

                    <div className="profile-item">
                        <label>이메일:</label>
                        <span>{user.email}</span>
                    </div>

                    <div className="profile-item">
                        <label>전화번호:</label>
                        <span>{user.phone}</span>
                    </div>

                    <div className="profile-item">
                        <label>봉사시간:</label>
                        <span>{user.serviceTime}</span>
                    </div>

                    <button className="edit-btn" onClick={handleEdit}>정보 수정</button>
                </div>
            </div>
            <Footer />
        </div>
    );
};

export default UserProfile;
