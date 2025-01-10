// BoardList.js
import React, { useState } from "react";
import "../../css/board/Board.css";
import { useNavigate } from "react-router-dom";

const BoardList = () => {

    const posts = [
        { id: 1, title: "React로 게시판 만들기", author: "John Doe", date: "2025-01-01", thumbnail: "/imges/thumbnail.png"},
        { id: 2, title: "React로 게시판 만들기", author: "John Doe", date: "2025-01-01", thumbnail: "/imges/thumbnail.png"},
        { id: 3, title: "JavaScript 상태 관리 비교", author: "Jane Smith", date: "2025-01-02", thumbnail: "/imges/thumbnail.png"},
        { id: 4, title: "Next.js의 장점과 활용", author: "Alice Brown", date: "2025-01-03", thumbnail: "/imges/thumbnail.png"}
    ];

    const [category, setCategory] = useState("all");
    const [boardStat , setBoardStat] =  useState("all");
    const [searchQuery, setSearchQuery] = useState("");

    // 글 목록 필터링 로직
    const filteredPosts = posts.filter((post) => {
        const matchesCategory =
            category === "all" || post.category === category;
        const matchesSearch = post.title
            .toLowerCase()
            .includes(searchQuery.toLowerCase());
        return matchesCategory && matchesSearch;
    });

    const navigate = useNavigate();

    // 글 작성 페이지로 이동
    const boardWritePage = () => {
        navigate("/boards/create")
    }

    return (
        <div className="boards-list">
            <h2>게시판 글목록</h2>

            {/* 검색 및 카테고리 선택 */}
            <div className="filters">
                <div className="filters-left">
                    <div className="boards-stat-div">
                        <span>글 상태 : </span>
                        <select
                            value={boardStat}
                            onChange={(e) => setBoardStat(e.target.value)}
                            className="board-stat-select filters-select"
                        >
                            <option value="all">전체</option>
                            <option value="tech">모집중</option>
                            <option value="life">모집마감</option>
                            <option value="news">종료</option>
                        </select>
                    </div>
                    <div className="category-div">
                        <span>카테고리 : </span>
                        <select
                            value={category}
                            onChange={(e) => setCategory(e.target.value)}
                            className="category-select filters-select"
                        >
                            <option value="all">전체</option>
                            <option value="tech">기술</option>
                            <option value="life">생활</option>
                            <option value="news">뉴스</option>
                        </select>
                    </div>
                    <div className="search-div">
                        <label htmlFor="searchId">검색어 : </label>
                        <input
                            type="text"
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                            placeholder="제목 검색"
                            id="searchId"
                            className="search-input"
                        />
                    </div>
                </div>
                <div className="filters-right">
                    <div className="boards-btn-div">
                        <button type="button" className="board-create-button" onClick={boardWritePage}>
                            글등록
                        </button>
                    </div>
                </div>
            </div>

            {/* 글 목록 */}
            <div className="boards-cards">
                {filteredPosts.map((post) => (
                    <div key={post.id} className="boards-card">
                        <img
                            src={post.thumbnail}
                            alt={`${post.title} 썸네일`}
                            className="boards-thumbnail"
                        />
                        <div className="boards-info">
                            <h3 className="boards-title">
                                <a href={`/boards/${post.id}`}>{post.title}</a>
                            </h3>
                            <p className="boards-author">작성자: {post.author}</p>
                            <p className="boards-date">{post.date}</p>
                        </div>
                    </div>
                ))}
            </div>

            {/* 검색 결과 없을 때 메시지 */}
            {filteredPosts.length === 0 && (
                <p className="no-results">검색 결과가 없습니다.</p>
            )}
        </div>
    );
};

export default BoardList;
