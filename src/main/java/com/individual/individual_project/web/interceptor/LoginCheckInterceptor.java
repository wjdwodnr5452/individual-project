package com.individual.individual_project.web.interceptor;

import com.individual.individual_project.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {

            // JSON 응답 반환
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // 응답 JSON 작성
            String jsonResponse = """
                {
                   "header": {
                       "code": 401,
                       "message": "로그인이 필요합니다."
                   },
                   "data": null,
                   "redirectURL": "/login",
                   "msg": "로그인이 필요합니다.",
                   "listMsg": null
                }
            """.formatted(requestURI);

            response.getWriter().write(jsonResponse);
            log.info("미인증 사용자 요청");
            return false;
        }
        return true;
    }
}
