// BoardList.js
import React, { useState } from "react";
import "../../css/board/Board.css";

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

    return (
        <div className="board-list">
            <h2>게시판 글목록</h2>

            {/* 검색 및 카테고리 선택 */}
            <div className="filters">
                <div className="board-stat-div">
                    <span>글 상태 : </span>
                    <select value={boardStat} onChange={(e) => setBoardStat(e.target.value)}
                            className="board-stat-select filters-select">
                        <option value="all">전체</option>
                        <option value="tech">모집중</option>
                        <option value="life">모집마감</option>
                        <option value="news">종료</option>
                    </select>
                </div>
                <div className="category-div">
                    <span>카테고리 : </span>
                    <select value={category} onChange={(e) => setCategory(e.target.value)}
                            className="category-select filters-select ">
                        <option value="all">전체</option>
                        <option value="tech">기술</option>
                        <option value="life">생활</option>
                        <option value="news">뉴스</option>
                    </select>
                </div>

                <div className="seach-div">
                    <label htmlFor="searchId">검색어 : </label>
                    <input type="text" value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)}
                           placeholder="제목 검색" id="searchId" className="search-input"/>
                </div>

                <div className="board-btn-div">
                    <button type="button" className="board-create-button">
                        글등록
                    </button>
                </div>
            </div>

            {/* 글 목록 */}
            <div className="post-cards">
                {filteredPosts.map((post) => (
                    <div key={post.id} className="post-card">
                        <img
                            src={post.thumbnail}
                            alt={`${post.title} 썸네일`}
                            className="post-thumbnail"
                        />
                        <div className="post-info">
                            <h3 className="post-title">
                                <a href={`/posts/${post.id}`}>{post.title}</a>
                            </h3>
                            <p className="post-author">작성자: {post.author}</p>
                            <p className="post-date">{post.date}</p>
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
