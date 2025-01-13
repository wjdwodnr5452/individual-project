import React, { useState } from "react";
import "../../css/board/BoardDetail.css";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Header from "../layout/Header";
import Footer from "../layout/Footer";


const BoardDetail = () => {
    const navigate = useNavigate();
    const currentUser = "wjdwodnr"; // 현재 로그인한 사용자 ID

// 가상 지원자 명단 예시
    const applicants = [
        { id: 1, name: "홍길동", phone: "010-1234-5678", date: "2025-01-10" },
        { id: 2, name: "김철수", phone: "010-2345-6789", date: "2025-01-12" },
        { id: 3, name: "박영희", phone: "010-3456-7890", date: "2025-01-15" },
    ];

    const boards = [
        {
            id: 1,
            title: "첫 번째 게시글",
            author: "wjdwodnr",
            serviceStat: "시작전",
            serviceDate: "2025-01-11",
            category: "환경보호",
            date: "2025-01-11",
            deadlineStat:"모집중",
            deadlineDate: "2025-01-20",
            content: "내용1",
            numberPeople: "5",
            thumbnail: "/images/thumbnail.png",
        }
    ];

    const { id } = useParams();
    const [boardsState, setBoardsState] = useState(boards);
    const board = boardsState.find((b) => b.id === parseInt(id, 10));
    const [serviceStat, setServiceStat] = useState(board.serviceStat);
    const [isModalOpen, setIsModalOpen] = useState(false); // 모달 상태 관리

    if (!board) {
        return <p>게시글을 찾을 수 없습니다.</p>;
    }

    const handleStatusChange = (e) => {
        const updatedBoards = boardsState.map((b) =>
            b.id === board.id ? { ...b, serviceStat: e.target.value } : b
        );
        setBoardsState(updatedBoards);
    };

    // 게시글 수정 페이지로 이동
    const boardDetailEditPage = (id) => {
        navigate("/boards/"+id+"/edit")
    }

    // 진행 상태 변경
    const boardStatBtn = (stat) => {
        setServiceStat(stat);
        alert(stat);
    }

    const showServicePeople = () => {
        setIsModalOpen(true); // 모달 열기
    };

    const closeModal = () => {
        setIsModalOpen(false); // 모달 닫기
    };

    return (
        <div>
            <Header />
            <div className="board-detail-page">
                <div className="board-header">
                    <h1 className="board-title">{board.title}</h1>
                    <div className="board-service-people">
                        <button className="board-service-people-button" onClick={() => showServicePeople()}>지원자 명단</button>
                    </div>
                    <div className="board-meta">
                        <div className="board-item">
                            <span className="board-label">작성자</span>
                            <span className="board-value">{board.author}</span>
                        </div>
                        <div className="board-item">
                            <span className="board-label">모집인원</span>
                            <span className="board-value">{board.numberPeople}</span>
                        </div>
                        <div className="board-item">
                            <span className="board-label">카테고리</span>
                            <span className="board-value">{board.category}</span>
                        </div>
                        <div className="board-item">
                            <span className="board-label">진행상태</span>
                            <span className="board-value">{board.serviceStat}</span>
                        </div>
                        <div className="board-item">
                            <span className="board-label">봉사일</span>
                            <span className="board-value">{board.date}</span>
                        </div>
                        <div className="board-item">
                            <span className="board-label">모집상태</span>
                            <span className="board-value">{board.deadlineStat}</span>
                        </div>
                        <div className="board-item">
                            <span className="board-label">마감일</span>
                            <span className="board-value">{board.deadlineDate}</span>
                        </div>
                        <div className="board-item">
                            <span className="board-label">작성일</span>
                            <span className="board-value">{board.date}</span>
                        </div>
                    </div>
                </div>

                <div className="board-thumbnail-container">
                    <img src={board.thumbnail} alt={`${board.title} 썸네일`} className="board-thumbnail"/>
                </div>

                <div className="board-content">
                    <p>{board.content}</p>
                </div>


                <div className="board-footer">

                    {board.author === currentUser ? (
                        <button className="board-editor-button" onClick={() => boardDetailEditPage(id)}>
                            수정하기
                        </button>
                    ) : (
                        <button className="board-apply-button" onClick={() => alert("지원하기 버튼 클릭")}>
                            지원하기
                        </button>
                    )}

                    {board.author === currentUser && (serviceStat != "종료" ? (
                        <div className="board-stat-div">
                            <button className="board-stat-button" onClick={() => boardStatBtn("종료")}>
                                종료
                            </button>
                        </div>

                    ) : (
                        <div className="board-stat-div">
                            <button className="board-stat-button" onClick={() => boardStatBtn("진행중")}>
                                진행
                            </button>
                        </div>
                    ))}

                    <button className="back-button" onClick={() => window.history.back()}>
                        뒤로가기
                    </button>
                </div>

                {/* 모달 */}
                {isModalOpen && (
                    <div className="modal-overlay">
                        <div className="modal-content">
                            <h2>지원자 명단</h2>
                            <div className="applicant-list">
                                {applicants.map((applicant) => (
                                    <div key={applicant.id} className="applicant-row">
                                        <p><strong>이름:</strong> {applicant.name}</p>
                                        <p><strong>전화번호:</strong> {applicant.phone}</p>
                                        <p><strong>지원날짜:</strong> {applicant.date}</p>
                                    </div>
                                ))}
                            </div>
                            <button className="close-modal" onClick={closeModal}>닫기</button>
                        </div>
                    </div>
                )}

            </div>
            <Footer/>
        </div>
    );
};

export default BoardDetail;
