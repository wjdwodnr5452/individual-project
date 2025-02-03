import React, { useEffect, useState } from "react";
import "../../css/board/Board.css";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../components/AuthProvider";

const BoardList = () => {
    const { isLoggedIn, user } = useAuth();

    const [categorys, setCategorys] = useState([]);
    const [category, setCategory] = useState("all");

    const [statServices, setStatServices] = useState([]);
    const [statService, setStatService] = useState("all");

    const [statRecruitments, setStatRecruitments] = useState([]);
    const [statRecruitment, setStatRecruitment] = useState("all");

    const [searchQuery, setSearchQuery] = useState("");

    const [pageSize, setPageSize] = useState("5");

    const [posts, setPosts] = useState([]);

    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(0);

    // 글 목록 데이터 가져오기
    const fetchServiceBoardList = async (page = currentPage) => {
        try {
            const params = new URLSearchParams();

            if (category && category !== "all") {
                params.append('categoryId', category); // 카테고리 파라미터 추가
            }

            if (statService && statService !== "all") {
                params.append('serviceStatId', statService); // 서비스 상태 파라미터 추가
            }

            if (statRecruitment && statRecruitment !== "all") {
                params.append('recruitStatId', statRecruitment); // 모집 상태 파라미터 추가
            }

            params.append("size", pageSize);
            params.append("page", page); // 페이지 파라미터 추가

            const response = await fetch(`/api/service/boards?${params.toString()}`);

            const responseData = await response.json();
            setPosts(responseData.data.content);
            setTotalPages(responseData.data.totalPages); // 전체 페이지 수 설정

        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        // 카테고리 데이터를 API로부터 받아오는 요청
        const fetchCategories = async () => {
            try {
                const response = await fetch("/api/categorys"); // API 요청 URL
                if (!response.ok) {
                    throw new Error("카테고리 데이터를 가져오는데 실패했습니다.");
                }
                const responseData = await response.json();
                setCategorys(responseData.data); // 받아온 데이터로 카테고리 상태 설정
            } catch (error) {
                console.error("Error fetching categories:", error);
            }
        };

        // 서비스상태 데이터 가져오기
        const fetchStatService = async () => {
            try {
                const response = await fetch("/api/status/service"); // API 요청 URL
                if (!response.ok) {
                    throw new Error("서비스 상태 데이터를 가져오는데 실패했습니다.");
                }
                const responseData = await response.json();
                setStatServices(responseData.data);
            } catch (error) {
                console.error(error);
            }
        };

        // 모집 상태 데이터 가져오기
        const fetchStatRecruitment = async () => {
            try {
                const response = await fetch("/api/status/recruitment"); // API 요청 URL
                if (!response.ok) {
                    throw new Error("모집 상태 데이터를 가져오는데 실패했습니다.");
                }
                const responseData = await response.json();
                setStatRecruitments(responseData.data);
            } catch (error) {
                console.error(error);
            }
        };

        // 초기 데이터 로딩
        fetchCategories();
        fetchStatService();
        fetchStatRecruitment();
    }, []);

    // 카테고리, 상태 변경 시 글 목록 업데이트
    useEffect(() => {
        fetchServiceBoardList(); // 상태값에 맞는 글 목록을 가져옴
    }, [category, statService, statRecruitment, pageSize, currentPage]); // 의존성 배열에 상태값 추가*/


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




    const navigate = useNavigate();

    // 글 작성 페이지로 이동
    const boardWritePage = () => {
        navigate("/boards/write");
    };


    // 페이지 네비게이션 핸들러
    const handlePageChange = (newPage) => {
        if (newPage >= 0 && newPage < totalPages) {
            setCurrentPage(newPage);
        }
    };

    return (
        <div className="boards-list">
            <h2>게시판 글목록</h2>

            {/* 검색 및 카테고리 선택 */}
            <div className="filters">
                <div className="filters-left">
                    <div className="boards-stat-div">
                        <span>진행 상태 : </span>
                        <select
                            value={statService}
                            onChange={(e) => setStatService(e.target.value)}
                            className="board-stat-select filters-select"
                        >
                            <option value="all">전체</option>
                            {statServices.map((statService) => (
                                <option key={statService.id} value={statService.id}>
                                    {statService.statusName}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="boards-recruit-stat-div">
                        <span>모집 상태 : </span>
                        <select
                            value={statRecruitment}
                            onChange={(e) => setStatRecruitment(e.target.value)}
                            className="board-recruit-stat-select filters-select"
                        >
                            <option value="all">전체</option>
                            {statRecruitments.map((statRe) => (
                                <option key={statRe.id} value={statRe.id}>
                                    {statRe.statusName}
                                </option>
                            ))}
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
                            {categorys.map((cat) => (
                                <option key={cat.id} value={cat.id}>
                                    {cat.categoryName}
                                </option>
                            ))}
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
                    <div className="page-size-div">
                        <span>페이지 갯수 : </span>
                        <select
                            value={pageSize}
                            onChange={(e) => setPageSize(e.target.value)}
                            className="page-size-select filters-select"
                        >
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="30">30</option>
                            <option value="40">40</option>
                            <option value="50">50</option>
                        </select>
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
            </div>

            {/* 글 목록 */}
            <div className="boards-cards">
            {posts.length > 0 ? (
                    posts.map((post) => (
                        <div key={post.id} className="boards-card">
                            <img
                                src={post.thumbnailImage || '/images/thumbnail.png'}
                                alt={`${post.title} 썸네일`}
                                className="boards-thumbnail"
                            />
                            <div className="boards-info">
                                <h3 className="boards-title">
                                    <a href={`/boards/${post.id}`}>{post.serviceTitle}</a>
                                </h3>
                                <div className="boards-meta">
                                    <p className="boards-author">작성자: {post.writer}</p>
                                    <p className="boards-category">카테고리: {post.categoryName}</p>
                                </div>
                                <div className="boards-details">
                                    <div className="details-row">
                                        <p className="boards-service-stat"><strong>진행상태:</strong> {post.serviceStatName}
                                        </p>
                                        <p className="boards-recruit-stat"><strong>모집상태:</strong> {post.recruitStatName}
                                        </p>
                                    </div>
                                    <div className="details-row">
                                        <p className="boards-service-date"><strong>봉사일:</strong> {formatDateTime(post.serviceDate)}</p>
                                        <p className="boards-recruit-deadline">
                                            <strong>모집마감:</strong>  {formatDateTime(post.deadline)}</p>
                                    </div>
                                    <div className="details-row">
                                        <p className="boards-service-time"><strong>봉사시간:</strong> {post.serviceTime}시간
                                        </p>
                                        <p className="boards-date"><strong>등록일:</strong> {formatDateTime(post.regDate)}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))
                ) : (
                    <p className="no-data">게시물이 없습니다.</p>
                )}
            </div>

            {/* 페이지 네비게이션 */}
            <div className="pagination">
                <button onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 0}>
                    이전
                </button>
                <span>
                    {currentPage + 1} / {totalPages}
                </span>
                <button onClick={() => handlePageChange(currentPage + 1)} disabled={currentPage === totalPages - 1}>
                    다음
                </button>
            </div>


        </div>
    );
};

export default BoardList;
