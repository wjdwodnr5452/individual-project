import React, { useState, useEffect } from "react";
import "../../css/board/BoardWrite.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Header from "../layout/Header";
import Footer from "../layout/Footer";

const BoardEdit = ({ postId }) => {
    const [title, setTitle] = useState("");
    const [category, setCategory] = useState("tech");
    const [content, setContent] = useState("");
    const [numberPeople, setNumberPeople] = useState("");
    const [deadline, setDeadline] = useState(new Date());
    const [serviceDate, setServiceDate] = useState(new Date());
    const [thumbnail, setThumbnail] = useState(null);
    const [serviceTime, setServiceTime] = useState("");

    // 게시글 데이터를 가져오는 함수 (예시: API 호출)
    useEffect(() => {
        // 여기에 실제 API 호출 코드 추가 (예시로 static 데이터 사용)
        const fetchPostData = async () => {
            const postData = {
                title: "기존 게시글 제목",
                category: "tech",
                content: "기존 게시글 내용",
                numberPeople: 5,
                deadline: new Date(),
                serviceDate: new Date(),
                thumbnail: null, // 또는 기존 썸네일 URL
            };
            setTitle(postData.title);
            setCategory(postData.category);
            setContent(postData.content);
            setNumberPeople(postData.numberPeople);
            setDeadline(postData.deadline);
            setServiceDate(postData.serviceDate);
            setThumbnail(postData.thumbnail);
        };
        fetchPostData();
    }, [postId]);

    const handleThumbnailChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setThumbnail(URL.createObjectURL(file));
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // 게시글 수정 처리 로직 (예시로 콘솔 출력)
        console.log({
            title,
            category,
            content,
            numberPeople,
            deadline,
            serviceDate,
            thumbnail,
        });
        alert("게시글이 수정되었습니다!");
    };

    return (
        <div>
            <Header />
            <div className="borad-write-container">
                <h2>게시글 수정하기</h2>
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
                            placeholder="모집인원을 입력하세요"
                            required
                        />
                    </div>

                    {/* 봉사시간 */}
                    <div className="borad-write-form-group">
                        <label htmlFor="writeServiceTime">봉사시간</label>
                        <input
                            type="number"
                            id="writeServiceTime"
                            value={serviceTime}
                            onChange={(e) => setServiceTime(e.target.value)}
                            placeholder="봉사 시간을 입력하세요"
                            required
                        />
                    </div>


                    {/* 마감일 */}
                    <div className="borad-write-form-group">
                        <label htmlFor="writeDeadline">마감일</label>
                        <DatePicker
                            selected={deadline}
                            onChange={(date) => setDeadline(date)}
                            dateFormat="yyyy-MM-dd HH:mm" // 날짜와 시간 형식
                            showTimeSelect // 시간 선택 활성화
                            timeFormat="HH:mm" // 시간 형식 설정 (24시간 기준)
                            timeIntervals={15} // 시간 간격 (15분 단위)
                            timeCaption="시간" // 시간 섹션의 캡션
                            placeholderText="마감일과 시간을 선택하세요"
                            id="writeDeadline"
                            required
                        />
                    </div>

                    {/* 봉사 시작일 */}
                    <div className="borad-write-form-group">
                        <label htmlFor="writeServiceDate">봉사일</label>
                        <DatePicker
                            selected={serviceDate}
                            dateFormat="yyyy-MM-dd HH:mm" // 날짜와 시간 형식
                            showTimeSelect // 시간 선택 활성화
                            timeFormat="HH:mm" // 시간 형식 설정 (24시간 기준)
                            timeIntervals={15} // 시간 간격 (15분 단위)
                            timeCaption="시간" // 시간 섹션의 캡션
                            placeholderText="봉사일과 시간을 선택하세요"
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
                        <button type="submit" className="submit-btn">수정하기</button>
                    </div>
                </form>
            </div>
            <Footer />
        </div>
    );
};

export default BoardEdit;
