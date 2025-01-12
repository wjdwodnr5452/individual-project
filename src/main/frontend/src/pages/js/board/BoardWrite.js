import React, { useState } from "react";
import "../../css/board/BoardWrite.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Header from "../layout/Header";
import Footer from "../layout/Footer";

const BoardWrite = () => {
    const [title, setTitle] = useState("");
    const [category, setCategory] = useState("tech");
    const [content, setContent] = useState("");
    const [numberPeople, setNumberPeople] = useState("");
    const [deadline, setDeadline] = useState(new Date());
    const [serviceDate, setServiceDate] = useState(new Date());
    const [thumbnail, setThumbnail] = useState(null);  // 썸네일 상태 추가

    const handleThumbnailChange = (e) => {
        const file = e.target.files[0];  // 선택된 파일
        if (file) {
            setThumbnail(URL.createObjectURL(file));  // 파일 URL을 상태에 저장
        }
    };


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
        <div>
            <Header/>
        <div className="borad-write-container">
            <h2>글 작성하기</h2>
            <form onSubmit={handleSubmit} className="board-write-form">
                {/* 제목 */}
                <div className="borad-write-form-group">
                    <label htmlFor="writeTitle">제목</label>
                    <input
                        type="text"
                        id="writeTitle"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        placeholder="글 제목을 입력하세요"
                        required
                    />
                </div>

                {/* 카테고리 */}
                <div className="borad-write-form-group">
                    <label htmlFor="writeCategory">카테고리</label>
                    <select
                        id="writeCategory"
                        value={category}
                        onChange={(e) => setCategory(e.target.value)}
                    >
                        <option value="tech">기술</option>
                        <option value="life">생활</option>
                        <option value="news">뉴스</option>
                    </select>
                </div>

                {/* 모집인원 */}
                <div className="borad-write-form-group">
                    <label htmlFor="writeNumberPeople">모집인원</label>
                    <input
                        type="number"
                        id="writeNumberPeople"
                        value={numberPeople}
                        onChange={(e) => setNumberPeople(e.target.value)}
                        placeholder="글 제목을 입력하세요"
                        required
                    />
                </div>

                {/* 마감일 */}
                <div className="borad-write-form-group">
                    <label htmlFor="writeDeadline">마감일</label>
                    <DatePicker
                        selected={deadline}
                        onChange={(date) => setDeadline(date)}
                        dateFormat="yyyy-MM-dd"
                        placeholderText="마감일을 선택하세요"
                        id="writeDeadline"
                        required
                    />
                </div>

                {/* 봉사 시작일 */}
                <div className="borad-write-form-group">
                    <label htmlFor="writeServiceDate">봉사일</label>
                    <DatePicker
                        selected={serviceDate}
                        onChange={(date) => setServiceDate(date)}
                        dateFormat="yyyy-MM-dd"
                        placeholderText="마감일을 선택하세요"
                        id="writeServiceDate"
                        required
                    />
                </div>

                {/* 내용 */}
                <div className="borad-write-form-group">
                    <label htmlFor="writeContent">내용</label>
                    <textarea
                        id="writeContent"
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        placeholder="글 내용을 입력하세요"
                        required
                    />
                </div>

                {/* 썸네일 이미지 */}
                <div className="borad-write-form-group">
                    <label htmlFor="writeThumbnail">썸네일</label>
                    <input
                        type="file"
                        id="writeThumbnail"
                        accept="image/*"
                        onChange={handleThumbnailChange}
                    />
                    {thumbnail && (
                        <div className="thumbnail-preview">
                            <img src={thumbnail} alt="썸네일 미리보기" />
                        </div>
                    )}
                </div>

                {/* 작성 버튼 */}
                <div className="board-write-form-actions">
                    <button type="submit" className="submit-btn">작성하기</button>
                </div>
            </form>
        </div>
            <Footer/>
        </div>
    );
};

export default BoardWrite;
