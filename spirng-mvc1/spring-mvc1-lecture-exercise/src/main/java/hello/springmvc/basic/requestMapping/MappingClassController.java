package hello.springmvc.basic.requestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingClassController {

    @GetMapping("/users")
    public String users(){
        return "get user";
    }

    @PostMapping("/users")
    public String addUser(){
        return "post user";
    }

    @GetMapping("/users/{userId}")
    public String findUser(@PathVariable String userId){
        return "get userId: "+userId;
    }

    @PatchMapping("/users/{userId}")
    public String updateUser(@PathVariable String userId){
        return  "update userId: "+userId;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable String userId){
        return  "delete userId: "+userId;
    }
}
