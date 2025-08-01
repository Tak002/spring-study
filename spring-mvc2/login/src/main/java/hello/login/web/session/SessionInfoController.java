package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            return "세션이 없습니다";
        }
        session.getAttributeNames().asIterator().forEachRemaining(
                attributeName -> log.info("attributeName:{}, value: {}",attributeName, session.getAttribute(attributeName)));
        log.info("session.getId() : {} ", session.getId());
        log.info("session.getMaxInactiveInterval() : {} ", session.getMaxInactiveInterval());
        log.info("session.getCreationTime() : {} ", new Date( session.getCreationTime()));
        log.info("session.getLastAccessedTime() : {} ", new Date(session.getLastAccessedTime()));
        log.info("session.isNew() : {} ", session.isNew());

        return "";
    }
}
