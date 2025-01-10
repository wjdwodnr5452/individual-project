import React, { useState } from "react";
import "../../css/board/BoardWrite.css";

const BoardWrite = () => {
    const [title, setTitle] = useState("");
    const [category, setCategory] = useState("tech");
    const [content, setContent] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log({
            title,
            category,
            content,
        });
        alert("글이 성공적으로 작성되었습니다!");
    };

    return (
        <div className="write-post-container">
            <h2>글 작성하기</h2>
            <form onSubmit={handleSubmit} className="write-post-form">
                {/* 제목 */}
                <div className="form-group">
                    <label htmlFor="title">제목</label>
                    <input
                        type="text"
                        id="title"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        placeholder="글 제목을 입력하세요"
                        required
                    />
                </div>

                {/* 카테고리 */}
                <div className="form-group">
                    <label htmlFor="category">카테고리</label>
                    <select
                        id="category"
                        value={category}
                        onChange={(e) => setCategory(e.target.value)}
                    >
                        <option value="tech">기술</option>
                        <option value="life">생활</option>
                        <option value="news">뉴스</option>
                    </select>
                </div>

                {/* 내용 */}
                <div className="form-group">
                    <label htmlFor="content">내용</label>
                    <textarea
                        id="content"
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        placeholder="글 내용을 입력하세요"
                        required
                    />
                </div>

                {/* 작성 버튼 */}
                <div className="form-actions">
                    <button type="submit" className="submit-btn">작성하기</button>
                </div>
            </form>
        </div>
    );
};

export default BoardWrite;
