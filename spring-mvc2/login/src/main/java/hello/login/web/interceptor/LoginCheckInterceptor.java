package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static hello.login.web.session.SessionConst.LOGIN_MEMBER;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 : {}", requestURI);
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(LOGIN_MEMBER) == null){
            log.info("미인증 사용자 요청 : {}", requestURI);
            response.sendRedirect("/login?redirectURL="+requestURI);
            return false;
        }
        return true;
    }

}
