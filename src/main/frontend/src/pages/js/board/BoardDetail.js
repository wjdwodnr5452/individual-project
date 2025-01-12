import React, { useState } from "react";
import "../../css/board/BoardDetail.css";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Header from "../layout/Header";
import Footer from "../layout/Footer";


const BoardDetail = () => {
    const navigate = useNavigate();
    const currentUser = "wjdwodnr"; // 현재 로그인한 사용자 ID
    const boards = [
        {
            id: 1,
            title: "첫 번째 게시글",
            author: "wjdwodnr",
            stat: "모집중",
            category: "청소",
            date: "2025-01-11",
            deadline: "2025-01-20",
            content: "내용1",
            numberPeople: "5",
            thumbnail: "/images/thumbnail.png",
        }
    ];

    const { id } = useParams();
    const [boardsState, setBoardsState] = useState(boards);
    const board = boardsState.find((b) => b.id === parseInt(id, 10));

    if (!board) {
        return <p>게시글을 찾을 수 없습니다.</p>;
    }

    const handleStatusChange = (e) => {
        const updatedBoards = boardsState.map((b) =>
            b.id === board.id ? { ...b, stat: e.target.value } : b
        );
        setBoardsState(updatedBoards);
    };

    // 게시글 수정 페이지로 이동
    const boardDetailEditPage = (id) => {
        navigate("/boards/"+id+"/edit")
    }


    return (
        <div>
            <Header />
            <div className="board-detail-page">
                <div className="board-header">
                    <h1 className="board-title">{board.title}</h1>
                    <div className="board-meta">
                        <div>
                            <span className="board-author">작성자 : {board.author}</span>
                        </div>
                        <div>
                            <span className="board-stat">글 상태 : {board.stat}</span>
                        </div>
                        <div>
                            <span className="board-number-people">모집인원 : {board.numberPeople}</span>
                        </div>
                        <div>
                            <span className="board-category">카테고리 : {board.category}</span>
                        </div>
                        <div>
                            <span className="board-date">봉사일 : {board.date}</span>
                        </div>
                        <div>
                            <span className="board-date">작성일 : {board.date}</span>
                        </div>
                        <div>
                            <span className="board-deadline">마감일 : {board.deadline}</span>
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

                    {board.author === currentUser && (
                        <div className="board-status-editor">
                        <label htmlFor="status">글 상태 수정: </label>
                            <select id="status" value={board.stat} onChange={handleStatusChange}>
                                <option value="모집중">모집중</option>
                                <option value="모집마감">모집마감</option>
                                <option value="종료">종료</option>
                            </select>
                        </div>
                    )}

                    <button className="back-button" onClick={() => window.history.back()}>
                        뒤로가기
                    </button>
                </div>
            </div>
            <Footer />
        </div>
    );
};

export default BoardDetail;
