// BoardList.js
import React, { useState } from "react";
import "../../css/board/Board.css";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../components/AuthProvider";

const BoardList = () => {

    const { isLoggedIn, user } = useAuth();

    const posts = [
        { id: 1, title: "React로 게시판 만들기", author: "John Doe", category:"환경보호", serviceStat:"시작전", serveiceDate:"2025-01-01", serveiceTime:"6",  recruitStat:"모집중", recruitDeadline:"2025-01-03",  date: "2025-01-01", thumbnail: "/images/thumbnail.png"},
        { id: 2, title: "React로 게시판 만들기", author: "John Doe", category:"환경보호", serviceStat:"시작전", serveiceDate:"2025-01-01", serveiceTime:"6",  recruitStat:"모집중", recruitDeadline:"2025-01-03",  date: "2025-01-01", thumbnail: "/images/thumbnail.png"},
        { id: 3, title: "JavaScript 상태 관리 비교", author: "Jane Smith", category:"환경보호",serviceStat:"시작전",serveiceDate:"2025-01-01",serveiceTime:"6", recruitStat:"모집중", recruitDeadline:"2025-01-03",   date: "2025-01-02", thumbnail: "/images/thumbnail.png"},
        { id: 4, title: "Next.js의 장점과 활용", author: "Alice Brown", category:"환경보호", serviceStat:"시작전", serveiceDate:"2025-01-01",serveiceTime:"6",  recruitStat:"모집중", recruitDeadline:"2025-01-03", date: "2025-01-03", thumbnail: "/images/thumbnail.png"}
    ];

    const [category, setCategory] = useState("all");
    const [boardStat , setBoardStat] =  useState("all");
    const [searchQuery, setSearchQuery] = useState("");
    const [boardReruit, setBoardRecruitStat] = useState("all");

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
        navigate("/boards/write")
    }

    return (
        <div className="boards-list">
            <h2>게시판 글목록</h2>

            {/* 검색 및 카테고리 선택 */}
            <div className="filters">
                <div className="filters-left">
                    <div className="boards-stat-div">
                        <span>진행 상태 : </span>
                        <select
                            value={boardStat}
                            onChange={(e) => setBoardStat(e.target.value)}
                            className="board-stat-select filters-select"
                        >
                            <option value="all">전체</option>
                            <option value="before_starting">시작전</option>
                            <option value="starting">진행중</option>
                            <option value="finish">종료</option>
                        </select>
                    </div>
                    <div className="boards-recruit-stat-div">
                        <span>모집 상태 : </span>
                        <select value={boardReruit}   onChange={(e) => setBoardRecruitStat(e.target.value)}  className="board-recruit-stat-select filters-select">
                            <option value="all">전체</option>
                            <option value="recruiting">모집중</option>
                            <option value="recruiting_deadline">모집마감</option>
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
                            <option value="environmental">환경보호</option>
                            <option value="social_welfare">사회복지</option>
                            <option value="education">교육</option>
                            <option value="culture_and_art">문화및예술</option>
                            <option value="animal_protection">동물보호</option>
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
                    {isLoggedIn && (
                        <div className="filters-right">
                            <div className="boards-btn-div">
                                <button type="button" className="board-create-button" onClick={boardWritePage}>
                                    글등록
                                </button>
                            </div>
                        </div>
                    )}
             < /div>

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
                            <div className="boards-meta">
                                <p className="boards-author">작성자: {post.author}</p>
                                <p className="boards-category">카테고리: {post.category}</p>
                            </div>
                            <div className="boards-details">
                                <div className="details-row">
                                    <p className="boards-service-stat"><strong>진행상태:</strong> {post.serviceStat}</p>
                                    <p className="boards-recruit-stat"><strong>모집상태:</strong> {post.recruitStat}</p>
                                </div>
                                <div className="details-row">
                                    <p className="boards-service-date"><strong>봉사일:</strong> {post.serveiceDate}</p>
                                    <p className="boards-recruit-deadline"><strong>모집마감:</strong> {post.recruitDeadline}</p>

                                </div>
                                <div className="details-row">
                                    <p className="boards-service-time"><strong>봉사시간:</strong> {post.serveiceTime}시간</p>
                                    <p className="boards-date"><strong>등록일:</strong> {post.date}</p>
                                </div>
                            </div>
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
