import React, { useEffect, useState } from "react";
import "../../css/board/BoardWrite.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {useNavigate} from "react-router-dom";

const BoardWrite = () => {
    const navigate = useNavigate();
    const [title, setTitle] = useState("");
    const [categorys, setCategorys] = useState([]);
    const [category, setCategory] = useState("");
    const [content, setContent] = useState("");
    const [recruitCount, setRecruitCount] = useState("");
    const [deadline, setDeadline] = useState(new Date());
    const [serviceDate, setServiceDate] = useState(new Date());
    const [thumbnail, setThumbnail] = useState(null); // 썸네일 상태 추가
    const [serviceTime, setServiceTime] = useState("");

    const handleThumbnailChange = (e) => {
        const file = e.target.files[0]; // 선택된 파일
        if (file) {
            setThumbnail(file); // 실제 파일 객체를 상태에 저장
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

        fetchCategories();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        // FormData 객체 생성
        const formData = new FormData();
        formData.append("title", title);
        formData.append("category", category);
        formData.append("content", content);
        formData.append("recruitCount", recruitCount);
        formData.append("deadline", deadline.toISOString()); // ISO 형식으로 변환
        formData.append("serviceDate", serviceDate.toISOString()); // ISO 형식으로 변환
        formData.append("serviceTime", serviceTime);


        if (thumbnail) {
            formData.append("thumbnail", thumbnail); // 파일 추가
        }

        try {
            // API 요청
            const response = await fetch("/api/service-boards", {
                method: "POST",
                body: formData,
            });

            const responseData = await response.json();

            if (!response.ok) {
                alert(responseData.msg);
            }else{
                alert("글이 성공적으로 작성되었습니다!");
                navigate("/boards/"+responseData.data.id);
            }

        } catch (error) {
            console.error("글 작성 중 에러 발생:", error);
            alert("글 작성 중 에러가 발생했습니다. 다시 시도해주세요.");
        }
    };

    return (
        <div>
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
                            <option value="">카테고리를 선택하세요</option>
                            {categorys.map((cat) => (
                                <option key={cat.id} value={cat.id}>
                                    {cat.categoryName}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* 모집인원 */}
                    <div className="borad-write-form-group">
                        <label htmlFor="writeRecruitCount">모집인원</label>
                        <input
                            type="number"
                            id="writeRecruitCount"
                            value={recruitCount}
                            onChange={(e) => setRecruitCount(e.target.value)}
                            placeholder="모집 인원을 입력하세요"
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
                        <label htmlFor="writeDeadline">마감날짜시간</label>
                        <DatePicker
                            selected={deadline}
                            onChange={(date) => setDeadline(date)}
                            dateFormat="yyyy-MM-dd HH:mm"
                            showTimeSelect
                            timeFormat="HH:mm"
                            timeIntervals={30}
                            timeCaption="시간"
                            placeholderText="마감일과 시간을 선택하세요"
                            id="writeDeadline"
                            required
                        />
                    </div>

                    {/* 봉사 시작일 */}
                    <div className="borad-write-form-group">
                        <label htmlFor="writeServiceDate">봉사날짜시간</label>
                        <DatePicker
                            selected={serviceDate}
                            onChange={(date) => setServiceDate(date)}
                            dateFormat="yyyy-MM-dd HH:mm"
                            showTimeSelect
                            timeFormat="HH:mm"
                            timeIntervals={30}
                            timeCaption="시간"
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
                                <img src={URL.createObjectURL(thumbnail)} alt="썸네일 미리보기" />
                            </div>
                        )}
                    </div>

                    {/* 작성 버튼 */}
                    <div className="board-write-form-actions">
                        <button type="submit" className="submit-btn">작성하기</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default BoardWrite;
