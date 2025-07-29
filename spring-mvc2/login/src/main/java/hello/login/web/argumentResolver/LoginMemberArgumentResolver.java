package hello.login.web.argumentResolver;

import hello.login.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static hello.login.web.session.SessionConst.LOGIN_MEMBER;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("@Login supportsParameter");
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberParameter = Member.class.isAssignableFrom(parameter.getParameterType());
        return hasMemberParameter && hasLoginAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("@Login resolveArgument");
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
//        HttpServletRequest nativeRequest = (HttpServletRequest) webRequest.getNativeRequest();
//        if(request==nativeRequest){
//            System.out.println("이게같구나");
//        }
        HttpSession session = request.getSession(false);
        if(session==null){
            return null;
        }
        return session.getAttribute(LOGIN_MEMBER);
    }
}
