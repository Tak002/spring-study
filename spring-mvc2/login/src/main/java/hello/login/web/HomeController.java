package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentResolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static hello.login.web.session.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @GetMapping("/")
    public String homeLoginV1(Model model, HttpServletRequest request) {
        Object member = sessionManager.getSession(request);
        if(member==null){
            return "home";
        }
        model.addAttribute("member",member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV2(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session==null){
            return "home";
        }
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if(member==null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(Model model, @SessionAttribute(name = LOGIN_MEMBER, required = false) Member member) {
        // @SessionAttribute는 getSession의 파리미터로 디폴트로 false를 사용한다.
        // getSession시에 create하고 싶다면 @SessionAttribute 대신 request.getSession을 사용해야 한다

        // 동작 방식: request.getSession(false): 이떄, cookie의 JSESSIONID 값을 조회, 존재시 해당 값으로 session 효출 시도
        //           session이 존재하여 호출이 성공하였을 경우, session.getAtrribute(LOGIN_MEMBER)실행 후 Member에 포멧팅

        if(member==null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member member,Model model) {

        if(member==null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
}
