import React, { useEffect, useState } from "react";
import "../../css/user/UserProfile.css";
import {useAuth} from "../../../components/AuthProvider";
import {useParams} from "react-router-dom";

const UserProfile = () => {

    const { isLoggedIn, user1 } = useAuth();

    const { id } = useParams();

    useEffect(() => {
        const fetchUserDetail = async () => {
            try {
                const response = await fetch(`/api/users/${id}`);
                const responseData = await response.json();
                alert(responseData.msg);
                setUser(responseData.data);
            } catch (err) {
                console.log("err : " , err);
            }
        }
        fetchUserDetail();
    }, [id]); // 게시글 ID가 변경될 때마다 호출


    const [user, setUser] = useState({});

    const applications = [
        { id: 1, title: "환경 보호 봉사활동", date: "2025-01-10" },
        { id: 2, title: "청소년 멘토링 프로그램", date: "2025-01-12" },
        { id: 3, title: "노인 복지 활동", date: "2025-01-15" },
    ];

    const posts = [
        { id: 1, title: "환경 보호 봉사활동", date: "2025-01-05" },
        { id: 2, title: "청소년 멘토링 프로그램", date: "2025-01-07" },
        { id: 3, title: "노인 복지 활동", date: "2025-01-09" },
    ];

    const [showApplications, setShowApplications] = useState(false); // 지원목록 표시 여부
    const [showMyPostsModal, setShowMyPostsModal] = useState(false); // 내가 쓴 게시글 모달 표시 여부

    const handleEdit = () => {
        alert("회원정보 수정 페이지로 이동합니다.");
    };

    const toggleApplications = () => {
        setShowApplications(!showApplications); // 지원목록 토글
    };


    const toggleMyPostsModal = () => {
        setShowMyPostsModal(!showMyPostsModal); // 내가 쓴 게시글 목록 모달 토글
    };

    return (
        <div>
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
                        <span>{user.phoneNumber}</span>
                    </div>

                    <div className="profile-item">
                        <label>봉사시간:</label>
                        <span>{user.totalServiceTime}</span>
                    </div>

                    {/* 지원목록 버튼 */}
                    <button className="applications-btn" onClick={toggleApplications}>
                        {showApplications ? "지원목록 숨기기" : "지원목록 보기"}
                    </button>

                    {/* 내가 쓴 게시글 버튼 */}
                    <button className="my-posts-btn" onClick={toggleMyPostsModal}>
                        내가 쓴 게시글 보기
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

                {/* 내가 쓴 게시글 모달 */}
                {showMyPostsModal && (
                    <div className="modal">
                        <div className="modal-content">
                            <span className="close-btn" onClick={toggleMyPostsModal}>&times;</span>
                            <h3>내가 쓴 게시글 목록</h3>
                            <ul className="posts-list">
                                {posts.map((post) => (
                                    <li key={post.id} className="post-item">
                                        <span>{post.title}</span>
                                        <span>({post.date})</span>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    </div>
                )}

            </div>
        </div>
    );
};

export default UserProfile;
