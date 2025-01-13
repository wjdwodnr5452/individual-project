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

    const applications = [
        { id: 1, title: "환경 보호 봉사활동", date: "2025-01-10" },
        { id: 2, title: "청소년 멘토링 프로그램", date: "2025-01-12" },
        { id: 3, title: "노인 복지 활동", date: "2025-01-15" },
    ];

    const [showApplications, setShowApplications] = useState(false); // 지원목록 표시 여부


    const handleEdit = () => {
        alert("회원정보 수정 페이지로 이동합니다.");
    };

    const toggleApplications = () => {
        setShowApplications(!showApplications); // 지원목록 토글
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

                    {/* 지원목록 버튼 */}
                    <button className="applications-btn" onClick={toggleApplications}>
                        {showApplications ? "지원목록 숨기기" : "지원목록 보기"}
                    </button>

                </div>

                {/* 지원목록 표시 */}
                {showApplications && (
                    <div className="applications-list">
                        <h3>지원한 게시글 목록</h3>
                        <ul>
                            {applications.map((app) => (
                                <li key={app.id} className="application-item">
                                    <span>{app.title}</span>
                                    <span>({app.date})</span>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}

            </div>
            <Footer />
        </div>
    );
};

export default UserProfile;
