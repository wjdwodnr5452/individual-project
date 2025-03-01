import React, { useEffect, useState } from "react";
import "../../css/user/UserProfile.css";
import {useAuth} from "../../../components/AuthProvider";
import {useParams} from "react-router-dom";

const UserProfile = () => {

    const { isLoggedIn, loginUser } = useAuth();
    const { id } = useParams();
    const [userApplicants, setUserApplicants] = useState([]);
    const [userServiceBoards, setUserServiceBoards] = useState([]);

    const formatDateTime = (dateString) => {
        const date = new Date(dateString);

        return new Intl.DateTimeFormat("ko-KR", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit",
            hour: "2-digit",
            minute: "2-digit",
            second: "2-digit",
        }).format(date);
    };

    useEffect(() => {
        const fetchUserDetail = async () => {
            try {
                const response = await fetch(`${process.env.REACT_APP_API_URL}/api/users/${id}`);
                const responseData = await response.json();

                if(responseData.header.code != 200){
                    alert(responseData.msg);
                }else{
                    setUser(responseData.data);
                }


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


    const toggleApplications = async (id) => {

      if(!showApplications && userApplicants.length == 0){
          try{
              const response = await fetch(`${process.env.REACT_APP_API_URL}/api/users/${id}/applicants`);
              const responseData = await response.json();

              if(responseData.header.code == 200) {
                  setUserApplicants(responseData.data);
              }else{
                  alert(responseData.msg);
              }
          }catch (error){
              alert(error);
          }
      }

        setShowApplications(!showApplications); // 지원목록 토글
    };


    const toggleMyPostsModal = async(id) => {

        if(!showMyPostsModal && userServiceBoards){

            try{
                const response = await fetch(`${process.env.REACT_APP_API_URL}/api/users/${id}/service-boards`);
                const responseData = await response.json();

                if(responseData.header.code == 200) {
                    setUserServiceBoards(responseData.data);
                }else{
                    alert(responseData.msg);
                }
            }catch (error){
                alert(error);
            }

        }


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

                    <div class="profile-btn">
                        {/* 지원목록 버튼 */}
                        <button className="user-profile-btn" onClick={() => toggleApplications(id)}>
                            {showApplications ? "지원목록 숨기기" : "지원목록 보기"}
                        </button>

                        {/* 내가 쓴 게시글 버튼 */}
                        <button className="user-profile-btn" onClick={() => toggleMyPostsModal(id)}>
                            내가 쓴 게시글 보기
                        </button>

                    </div>


                </div>

                {/* 지원목록 표시 */}
                {showApplications && (
                    <div className="modal">
                        <div className="modal-content">
                            <span className="close-btn" onClick={toggleApplications}>&times;</span>
                            <h3>내가 쓴 게시글 목록</h3>
                            <ul className="applicants-list">
                                {userApplicants.map((app) => (
                                    <li key={app.id} className="applicants-item">
                                        <span>{app.serviceBoardTitle}</span>
                                        <span>({app.applicantStatusName})</span>
                                        <span>({app.userServiceTime} 시간 부여)</span>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    </div>




             /*       <div className="applications-list">
                        <h3>지원한 게시글 목록</h3>
                        <ul>
                            {userApplicants.map((app) => (
                                <li key={app.id} className="application-item">
                                    <span>{app.serviceBoardTitle}</span>
                                    <span>({app.applicantStatusName})</span>
                                    <span>({app.userServiceTime} 시간 부여)</span>
                                    {/!*   <span>({app.date})</span>*!/}
                                </li>
                            ))}
                        </ul>
                    </div>*/
                )}

                {/* 내가 쓴 게시글 모달 */}
                {showMyPostsModal && (
                    <div className="modal">
                        <div className="modal-content">
                            <span className="close-btn" onClick={toggleMyPostsModal}>&times;</span>
                            <h3>내가 쓴 게시글 목록</h3>
                            <ul className="posts-list">
                                {userServiceBoards.map((serviceBoard) => (
                                    <li key={serviceBoard.id} className="post-item">
                                        <span>{serviceBoard.serviceTitle}</span>
                                        <span>({formatDateTime(serviceBoard.regDate)})</span>
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
