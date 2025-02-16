import React, {useEffect, useState} from "react";
import "../../css/board/BoardDetail.css";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../components/AuthProvider"; // AuthContext 사용

const BoardDetail = () => {
    const { isLoggedIn, user } = useAuth();
    const [boardDetail, setBoardDetail] = useState({});
    const navigate = useNavigate();



// 가상 지원자 명단 예시
    const [applicants,setApplicants] = useState([]);


    const [hasApplied, setHasApplied] = useState(false);
    const [userApplicantStat, setUserApplicantStat] = useState(7);
    const [userApplicantId, setUserApplicantId] = useState();


    const { id } = useParams();

    useEffect(() => {
        // 서버에서 게시글 데이터 가져오기
        const fetchBoardDetail = async () => {
            try {
                const response = await fetch(`/api/service-boards/${id}`);
                const responseData = await response.json();

                if(responseData.header.code == 200) {
                    setBoardDetail(responseData.data);
                }else{
                    alert(responseData.msg);
                }

            } catch (err) {
                console.log("err : " , err);
            }
        };


        // 신청자 수 가져오기
 /*       const fetchApplicantCount = async () => {
            try {
                const response = await fetch(`/api/service/boards/${id}`);
                const responseData = await response.json();

                if(responseData.header.code == 200) {
                    setBoardDetail(responseData.data);
                }else{
                    alert(responseData.msg);
                }

            } catch (err) {
                console.log("err : " , err);
            }
        };*/

        fetchBoardDetail();
    }, [id]); // 게시글 ID가 변경될 때마다 호출

    useEffect(() => {
        const checkApplicationStatus = async () => {
            try {
                if (isLoggedIn && user) {

                    // 여기서 API 요청하여 지원 상태 확인
                    const response = await fetch(`/api/applicants/${user.id}/${id}`);
                    const responseData = await response.json();

                    if(responseData.data != null){
                        setHasApplied(true);
                        setUserApplicantId(responseData.data.applicantId);
                        setUserApplicantStat(responseData.data.applicantStatId);
                    }
                } else {
                    console.log("로그인 안됨 또는 user 정보 없음");
                }
            } catch (error) {
                console.error("Error checking application status:", error);
            }
        };

        if (isLoggedIn && user) {
            checkApplicationStatus();
        }
    }, [id, isLoggedIn, user]); // 로그인 상태 및 사용자 정보가 변경될 때마다 실행


    const [isModalOpen, setIsModalOpen] = useState(false); // 모달 상태 관리


    // 게시글 수정 페이지로 이동
    const boardDetailEditPage = (id) => {
        navigate("/boards/"+id+"/edit")
    }

    // 진행 상태 변경
    const boardStatBtn = (stat) => {
       // setServiceStat(stat);
        alert(stat);
    }

    const showServicePeople = async (id) => {

        const response = await fetch(`/api/service-boards/${id}/applicants`);
        const responseData = await response.json();

        console.log("responseData.data  : " , responseData.data);

        setApplicants(responseData.data);

        setIsModalOpen(true); // 모달 열기
    };

    const closeModal = () => {
        setIsModalOpen(false); // 모달 닫기
    };

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

    const boardApplicantBtn = async (id,status) => {
        if (!isLoggedIn) {
            navigate("/login");
            return;
        }

        try {
            const apiUrl = "/api/applicants";
            const requestUrl = hasApplied ? `${apiUrl}/${userApplicantId}/status`:  `/api/service-boards/${id}/applicants` ;
            const method = hasApplied ? "PATCH" : "POST";

            const response = await fetch(requestUrl, {
                method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(status),
            });

            const responseData = await response.json();

            if (response.ok) {
                alert(responseData.msg);
                if(responseData.data != null){
                    setHasApplied(true);
                    setUserApplicantId(responseData.data.applicantId);
                    setUserApplicantStat(responseData.data.applicantStatId);
                }
            } else {
                alert(responseData.msg);
            }
        } catch (error) {
            console.error("Error during application:", error);
            alert("오류가 발생했습니다.");
        }
    };

    return (
        <div>
            {Object.keys(boardDetail).length > 0 ? (
                <div className="board-detail-page">
                    <div className="board-header">
                        <h1 className="board-title">{boardDetail.serviceTitle}</h1>

                        {isLoggedIn && boardDetail.writerCheck && (
                            <div className="board-service-people">
                                <button className="board-service-people-button" onClick={() => showServicePeople(id)}>
                                    지원자 명단
                                </button>
                            </div>
                        )}


                        <div className="board-meta">
                            <div className="board-item">
                            <span className="board-label">작성자</span>
                                <span className="board-value">{boardDetail.writer}</span>
                            </div>
                            <div className="board-item">
                                <span className="board-label">모집인원</span>
                                <span className="board-value">{boardDetail.recruitCount}</span>
                            </div>
                            <div className="board-item">
                                <span className="board-label">봉사시간</span>
                                <span className="board-value">{boardDetail.serviceTime}</span>
                            </div>
                            <div className="board-item">
                                <span className="board-label">카테고리</span>
                                <span className="board-value">{boardDetail.categoryName}</span>
                            </div>
                            <div className="board-item">
                                <span className="board-label">진행상태</span>
                                <span className="board-value">{boardDetail.serviceStatName}</span>
                            </div>
                            <div className="board-item">
                                <span className="board-label">봉사일</span>
                                <span className="board-value">{formatDateTime(boardDetail.serviceDate)}</span>
                            </div>
                            <div className="board-item">
                                <span className="board-label">모집상태</span>
                                <span className="board-value">{boardDetail.recruitStatName}</span>
                            </div>
                            <div className="board-item">
                                <span className="board-label">마감일</span>
                                <span className="board-value">{formatDateTime(boardDetail.deadline)}</span>
                            </div>
                            <div className="board-item">
                                <span className="board-label">작성일</span>
                                <span className="board-value">{formatDateTime(boardDetail.regDate)}</span>
                            </div>
                        </div>
                    </div>

                    <div className="board-thumbnail-container">
                        <img src={boardDetail.thumbnailImage} alt={`${boardDetail.serviceTitle} 썸네일`} className="board-thumbnail" />
                    </div>

                    <div className="board-content">
                        <p>{boardDetail.serviceContent}</p>
                    </div>

                    <div className="board-footer">
                        {isLoggedIn && boardDetail.writerCheck ? (
                            <button className="board-editor-button" onClick={() => boardDetailEditPage(id)}>
                                수정하기
                            </button>
                        ) : (
                            hasApplied ? (
                                userApplicantStat == 6 ||  userApplicantStat == 7 ? (
                                    <button className="board-apply-button" onClick={() => boardApplicantBtn(userApplicantId,8)}>
                                        취소하기
                                    </button>
                                ) : (
                                    <button className="board-apply-button" onClick={() => boardApplicantBtn(userApplicantId, 6)}>
                                        지원하기
                                    </button>
                                )
                            ) : (
                                <button className="board-apply-button" onClick={() => boardApplicantBtn(boardDetail.id)}>
                                    지원하기
                                </button>
                            )
                        )}

                        {boardDetail.writerCheck && (
                            boardDetail.serviceStatId === 4 ? (
                                <div className="board-stat-div">
                                    <button className="board-stat-button" onClick={() => boardStatBtn("종료")}>
                                        종료
                                    </button>
                                </div>
                            ) : boardDetail.serviceStatId === 5 ? (
                                <div className="board-stat-div">
                                    <button className="board-stat-button" onClick={() => boardStatBtn("진행중")}>
                                        진행
                                    </button>
                                </div>
                            ) : null
                        )}

                        <button className="back-button" onClick={() => window.history.back()}>
                            뒤로가기
                        </button>
                    </div>

                    {isModalOpen && (
                        <div className="modal-overlay">
                            <div className="modal-content">
                                <h2>지원자 명단</h2>
                                <div className="applicant-list">
                                    {applicants.map((applicant) => (
                                        <div key={applicant.id} className="applicant-row">
                                            <p><strong>이름:</strong> {applicant.userName}</p>
                                            <p><strong>전화번호:</strong> {applicant.phoneNumber}</p>
                                            <p><strong>지원날짜:</strong> {applicant.applicantDate}</p>
                                            {/* 거절 버튼 추가 */}
                                            <button
                                                className="reject-button">
                                                거절
                                            </button>
                                        </div>
                                    ))}
                                </div>
                                <button className="close-modal" onClick={closeModal}>닫기</button>
                            </div>
                        </div>
                    )}
                </div>
            ) : (
                <p>데이터 로딩 중입니다...</p>
            )}
        </div>
    );
};

export default BoardDetail;
