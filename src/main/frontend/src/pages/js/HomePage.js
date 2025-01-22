import React from 'react';
import "../css/Hompage.css"; // CSS 모듈 임포트
import Header from "./layout/Header";  // 올바른 경로로 수정
import Footer from "./layout/Footer";
import BoardList from "./board/BoardList";

function HomePage() {
    return (
        <div>

            {/*카테고리 및 검색*/}
            <BoardList/>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        </div>
    );
}

export default HomePage;