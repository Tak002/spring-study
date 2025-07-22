package hello.springmvc.requeset;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username,age);
        response.getWriter().write("ok");
        return;
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberUsername,
            @RequestParam("age") int memberAge){
        log.info("username={}, age={}",memberUsername,memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam("username") String username,
            @RequestParam("age") int age){
        log.info("username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age){
        log.info("requestParamV4 : username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(value = "username",required = true) String username, //username= 로 쿼리를 날리면 username = ""
            @RequestParam(value = "age",required = false) Integer age){
        log.info("username={}, age={}",username,age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(value = "username",defaultValue = "한글") String username, //username= 로 쿼리를 날리면 username = defaultValue
            @RequestParam(value = "age",defaultValue = "20") Integer age){
        log.info("username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> map){
        String username = (String) map.get("username");
        Integer age = Integer.parseInt((String)map.get("age"));

        log.info("username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-multimap")
    public String requestParamMultiMap(@RequestParam MultiValueMap<String, Object> map){
        List<Object> username = map.get("username");
        List<Object> age = map.get("age");

        log.info("username={}, age={}",username,age);
        return "ok";
    }
}
