package com.individual.individual_project.web.interceptor;

import com.individual.individual_project.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private static final Pattern EDIT_PATTERN = Pattern.compile("^/api/service-boards/\\d+/edit$");
    private static final Pattern USER_PATTERN = Pattern.compile("^/api/users/\\d+");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession(false);

        if("POST".equalsIgnoreCase
                (request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod()) || "DELETE".equalsIgnoreCase(request.getMethod()) ){

            if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {

                // JSON 응답 반환
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // 응답 코드 만들기
                String jsonResponse = sessionNullResponseString(requestURI);

                response.getWriter().write(jsonResponse);
                log.info("미인증 사용자 요청");
                return false;
            }
            return true;

        }else{
            Matcher editMatcher = EDIT_PATTERN.matcher(requestURI);
            Matcher userMatcher = USER_PATTERN.matcher(requestURI);
            if (editMatcher.matches() || userMatcher.matches()) {
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {

                    // JSON 응답 반환
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    // 응답 코드 만들기
                    String jsonResponse = sessionNullResponseString(requestURI);

                    response.getWriter().write(jsonResponse);
                    log.info("미인증 사용자 요청");
                    return false;
                }
            }
            return true;
        }
    }

    private String sessionNullResponseString(String requestURI) {

        // 응답 JSON 작성
        String returnResponse = """
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

        return returnResponse;

    }

}
