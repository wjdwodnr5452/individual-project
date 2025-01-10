// BoardDetail.js
import React from "react";
import "../../css/board/BoardDetail.css";
import { useParams } from "react-router-dom";
import Header from "../layout/Header";
import Footer from "../layout/Footer";

//const BoardDetail = ({ board }) => {
const BoardDetail = () => {
    const boards = [
        { id: 1, title: "첫 번째 게시글", content: "내용1" , thumbnail:"/imges/thumbnail.png"},
        { id: 2, title: "두 번째 게시글", content: "내용2" },
    ];

    const { id } = useParams();

    const board = boards.find((b) => b.id === parseInt(id, 10));

    if (!board) {

        return <p>게시글을 찾을 수 없습니다.</p>;
    }

    return (
        <div>
            <Header/>
        <div className="board-detail-page">
            <div className="board-header">
                <h1 className="board-title">{board.title}</h1>
                <div className="board-meta">
                    <span className="board-author">작성자: {board.author}</span>
                    <span className="board-date">{board.date}</span>
                </div>
            </div>

            <div className="board-thumbnail-container">
                <img
                    src={board.thumbnail}
                    alt={`${board.title} 썸네일`}
                    className="board-thumbnail"
                />
            </div>

            <div className="board-content">
                <p>{board.content}</p>
            </div>

            <div className="board-footer">
                <button className="back-button" onClick={() => window.history.back()}>
                    뒤로가기
                </button>
            </div>
        </div>
            <Footer/>
        </div>
    );
};

export default BoardDetail;
