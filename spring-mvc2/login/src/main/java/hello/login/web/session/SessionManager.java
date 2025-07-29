package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String,Object> sessionStore = new ConcurrentHashMap<>();


    /**
     * session 생성
     * *session id 생성
     * *session store에 session id와 보관할 값 저장
     * *session id로 응답 쿠기 생성 후 전달
     */
    public void createSession(Object value, HttpServletResponse response){
        String sessionId = String.valueOf(UUID.randomUUID());
        sessionStore.put(sessionId, value);

        Cookie mySessionId = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionId);
    }

    public Object getSession(HttpServletRequest request){
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if(cookie == null){
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    public void expire(HttpServletRequest request){
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if(cookie != null){
            sessionStore.remove(cookie.getValue());
        }
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        return  Arrays.stream(cookies).filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }
}
