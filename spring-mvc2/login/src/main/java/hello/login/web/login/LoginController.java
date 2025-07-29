package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static hello.login.web.session.SessionConst.LOGIN_MEMBER;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult){
        return "/login/loginForm";
    }

//    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail");
            return "/login/loginForm";
        }

        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);
        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV2(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail");
            return "/login/loginForm";
        }
        sessionManager.createSession(loginMember,response);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV3(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail");
            return "/login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginV3Spring(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL){
        if(bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail");
            return "/login/loginForm";
        }
//        sessionManager.createSession(loginMember,response);
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, loginMember);
        return "redirect:"+redirectURL;
    }

//    @PostMapping("/logout")
    public String logoutV1(HttpServletRequest request,HttpServletResponse response){
        sessionManager.expire(request);
//        expireCookie(response, SESSION_COOKIE_NAME); 이 코드를 뺴서, 클라이언트에서 sessionId를 쿠키를 보내도, 쿠키를 검증하면서 로그인 실패함.
        return "redirect:/";
    }


    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
    private static void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
