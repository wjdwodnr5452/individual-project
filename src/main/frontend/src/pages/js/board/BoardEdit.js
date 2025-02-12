import React, { useState, useEffect } from "react";
import "../../css/board/BoardWrite.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {useNavigate, useParams} from "react-router-dom";


const BoardEdit = () => {
    const navigate = useNavigate();
    const { id } = useParams();

    const [title, setTitle] = useState("");
    const [category, setCategory] = useState("tech");
    const [content, setContent] = useState("");
    const [recruitCount, setRecruitCount] = useState("");
    const [deadline, setDeadline] = useState(new Date());
    const [serviceDate, setServiceDate] = useState(new Date());
    const [thumbnail, setThumbnail] = useState(null);
    const [serviceTime, setServiceTime] = useState("");
    const [changeThumnail, setChangeThumnail] = useState(false);
    const [serviceStatId, setStatServiceId] = useState(4);

    // 게시글 데이터를 가져오는 함수 (예시: API 호출)
    useEffect(() => {
        // 여기에 실제 API 호출 코드 추가 (예시로 static 데이터 사용)
        const fetchBoardDetailEdit = async () => {
            try {
                const response = await fetch(`/api/service-boards/${id}/edit`);
                const responseData = await response.json();

                console.log("responseData : " , responseData);

                if(responseData.header.code != 200){
                    alert(responseData.msg);
                    navigate("/boards/"+id);
                }

                setTitle(responseData.data.serviceTitle);
                setCategory(responseData.data.cateGoryId);
                setContent(responseData.data.serviceContent);
                setRecruitCount(responseData.data.recruitCount);
                setDeadline(new Date(responseData.data.deadline));
                setServiceDate(new Date(responseData.data.serviceDate));
                setThumbnail(responseData.data.thumbnailImage);
                setServiceTime(responseData.data.serviceTime);
                setStatServiceId(responseData.data.serviceStatId);
            } catch (err) {
                console.log("err : " , err);
            }
        };
        fetchBoardDetailEdit();
    }, [id]);

    const handleThumbnailChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setThumbnail(file);
            setChangeThumnail(true);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const offset = new Date().getTimezoneOffset() * 60000;
        const deadlineFormDate = new Date(deadline - offset);
        const serviceDateFormDate = new Date(serviceDate - offset);

        // FormData 객체 생성
        const formData = new FormData();
        formData.append("title", title);
        formData.append("category", category);
        formData.append("content", content);
        formData.append("recruitCount", recruitCount);
        formData.append("deadline", deadlineFormDate.toISOString()); // ISO 형식으로 변환
        formData.append("serviceDate", serviceDateFormDate.toISOString()); // ISO 형식으로 변환
        formData.append("serviceTime", serviceTime);

        // 기존 썸네일이 변경되었는지 확인
        if (thumbnail && setChangeThumnail) {
            // 새로 업로드된 이미지 파일인 경우 Multipart로 전달
            formData.append("thumbnail", thumbnail);
        }
        try {
            // API 요청
            const response = await fetch(`/api/service-boards/${id}/edit`, {
                method: "PUT",
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
                            value={recruitCount}
                            onChange={(e) => setRecruitCount(e.target.value)}
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

                    {serviceStatId === 3 && (
                        <div className="borad-write-form-group">
                            <label htmlFor="writeDeadline">마감날짜시간</label>
                            <DatePicker
                                selected={deadline}
                                onChange={(date) => setDeadline(date)}
                                dateFormat="yyyy-MM-dd HH:mm" // 날짜와 시간 형식
                                showTimeSelect // 시간 선택 활성화
                                timeFormat="HH:mm" // 시간 형식 설정 (24시간 기준)
                                timeIntervals={30} // 시간 간격 (15분 단위)
                                timeCaption="시간" // 시간 섹션의 캡션
                                placeholderText="마감일과 시간을 선택하세요"
                                id="writeDeadline"
                                required
                            />
                        </div>
                    )}

                    {serviceStatId === 3 && (

                        <div className="borad-write-form-group">
                            <label htmlFor="writeServiceDate">봉사날짜시간</label>
                            <DatePicker
                                selected={serviceDate}
                                onChange={(date) => setServiceDate(date)}
                                dateFormat="yyyy-MM-dd HH:mm" // 날짜와 시간 형식
                                showTimeSelect // 시간 선택 활성화
                                timeFormat="HH:mm" // 시간 형식 설정 (24시간 기준)
                                timeIntervals={30} // 시간 간격 (15분 단위)
                                timeCaption="시간" // 시간 섹션의 캡션
                                placeholderText="봉사일과 시간을 선택하세요"
                                id="writeServiceDate"
                                required
                            />
                        </div>


                    )}


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
                            changeThumnail ? (
                                <div className="thumbnail-preview">
                                    <img src={URL.createObjectURL(thumbnail)} alt="썸네일 미리보기"/>
                                </div>
                            ) : (
                                <div className="thumbnail-preview">
                                    <img src={thumbnail} alt="썸네일 미리보기"/>
                                </div>
                            )
                        )}
                    </div>

                    {/* 작성 버튼 */}
                    <div className="board-write-form-actions">
                        <button type="submit" className="submit-btn">수정하기</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default BoardEdit;
