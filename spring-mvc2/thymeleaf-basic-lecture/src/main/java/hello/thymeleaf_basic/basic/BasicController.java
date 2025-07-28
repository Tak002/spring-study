package hello.thymeleaf_basic.basic;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @RequestMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data","Hello World");
        return "basic/text-basic";
    }

    @RequestMapping("/text-unescaped")
    public String textUnescape(Model model){
        model.addAttribute("data","<b>Hello World<b>");
        return "basic/text-unescaped";
    }

    @RequestMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 10);

        List<User> users = new ArrayList<User>();
        users.add(userA);
        users.add(userB);

        Map<String,User> userMap = new HashMap<>();
        userMap.put("userA",userA);
        userMap.put("userB",userB);

        model.addAttribute("userA",userA);
        model.addAttribute("users",users);
        model.addAttribute("userMap",userMap);

        return  "basic/variable";
    }

    @RequestMapping("basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData","Hello world!");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    public static class HelloBean {
        public String hello(String data){
            return "Hello! "+data;
        }
    }

    @Data
    static class User{
        private String username;
        private Integer age;

        public User(String username, Integer age) {
            this.username = username;
            this.age = age;
        }
    }
}
