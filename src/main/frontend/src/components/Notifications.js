import { useEffect, useRef, useState } from "react";
import { createPortal } from "react-dom";

export default function Notifications() {
    const [toasts, setToasts] = useState([]);
    const esRef = useRef(null);

    useEffect(() => {
        // 백엔드 SSE 엔드포인트에 연결
        const eventSource = new EventSource("http://localhost:8081/subscribe/applicant", { withCredentials: true});
        esRef.current = eventSource;

        // Kafka Consumer → SSE → React 로 전달된 메시지 수신
        eventSource.addEventListener("applicant-message", (event) => {
            try {
                const data = JSON.parse(event.data);
                setToasts((prev) => [...prev, data]);

                // 10초 후 자동으로 알림 제거
                setTimeout(() => {
                    setToasts((prev) => prev.slice(1));
                }, 10000);
            } catch (err) {
                console.error("알림 파싱 실패:", err);
            }
        });

        eventSource.onerror = (err) => {
            console.error("SSE 연결 오류:", err);
            eventSource.close();
        };

        return () => {
            eventSource.close();
            esRef.current = null;
        };
    }, []);

    // body에 포털 형태로 띄우기 (모든 페이지 위에 오버레이)
    return createPortal(
        <div
            style={{
                position: "fixed",
                right: "16px",
                bottom: "16px",
                display: "flex",
                flexDirection: "column",
                gap: "10px",
                zIndex: 9999,
            }}
        >
            {toasts.map((toast, i) => (
                <div
                    key={i}
                    style={{
                        backgroundColor: "#fff",
                        borderRadius: "10px",
                        padding: "12px 16px",
                        boxShadow: "0 4px 15px rgba(0,0,0,0.15)",
                        minWidth: "250px",
                        transition: "all 0.3s ease",
                    }}
                >
                    <strong>지원자 알림</strong>
                    <div style={{ marginTop: "6px", fontSize: "14px" }}>
                        게시글 <b>{toast.serviceBoardTitle}</b>에
                        지원자 <b>{toast.applicantUserName}</b>가 {toast.statusName}했습니다.
                    </div>
                </div>
            ))}
        </div>,
        document.body
    );
}
