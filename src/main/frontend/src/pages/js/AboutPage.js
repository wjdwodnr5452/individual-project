import React from 'react';
import { Link } from 'react-router-dom';

function AboutPage() {
    return (
        <div>
            <h2>About 페이지</h2>
            <p>이 페이지는 About 페이지입니다.</p>
            <Link to="/">홈 페이지로 이동</Link>
        </div>
    );
}

export default AboutPage;