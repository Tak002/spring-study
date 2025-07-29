package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static hello.login.web.session.SessionConst.LOGIN_MEMBER;

@Slf4j
public class LoginCheckFilter implements Filter {
    public static String[] whiteList = {"/", "/login", "/logout", "/members/add", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();
        try {
            log.info("로그인 인증 시작");
            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행 : {}", requestURI);
                HttpSession session = httpServletRequest.getSession();
                if(session == null || session.getAttribute(LOGIN_MEMBER) == null){
                    log.info("미인증 사용자 요청 : {}", requestURI);
                    httpServletResponse.sendRedirect("/login?redirectURL="+requestURI);
                    return;
                }
            }
            chain.doFilter(request,response);
        }catch (Exception e){
            throw e; //예외 로깅 가능 하지만, 톰캣까지 예외를 보내주어야 함
        }finally {
            log.info("인증 체크 필터 종료");
        }

    }

    /**
     * 화이트리스트 로그인 체크 무시 로직
     */
    private boolean isLoginCheckPath(String requestURI){
//        boolean isWhiteList = whiteList.contains(requestURI);
//        return !isWhiteList;
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
