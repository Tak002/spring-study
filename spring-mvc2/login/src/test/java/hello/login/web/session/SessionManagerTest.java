package hello.login.web.session;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;
import java.util.Arrays;

import static hello.login.web.session.SessionManager.SESSION_COOKIE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void createSession() {
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        sessionManager.createSession("hello",mockHttpServletResponse);
        Cookie cookie = Arrays.stream(mockHttpServletResponse.getCookies()).filter(c -> c.getName().equals(SESSION_COOKIE_NAME))
                .findFirst()
                .orElseThrow(AssertionError::new);
        assertThat(cookie).isNotNull();

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setCookies(mockHttpServletResponse.getCookies());

        Object object = sessionManager.getSession(mockHttpServletRequest);
        assertThat(object).isEqualTo("hello");


        sessionManager.expire(mockHttpServletRequest);
        Object session = sessionManager.getSession(mockHttpServletRequest);
        assertThat(session).isNull();
    }

    @Test
    void getSession() {
    }

    @Test
    void expire() {
    }

    @Test
    void findCookie() {
    }
}