package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;


@Slf4j
@Controller
public class ResponseBodyController {

    @RequestMapping("/response-body-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @RequestMapping("/response-body-v2")
    public ResponseEntity<String> responseBodyV2()  {
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/response-body-v3")
    public String responseBodyV3()  {
        return "ok";
    }

    @RequestMapping("/response-json-v1")
    public ResponseEntity<HelloData> responseJsonV1()  {
        HelloData helloData = new HelloData();
        helloData.setUsername("admin");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping("/response-json-v2")
    public HelloData responseJsonV2()  {
        HelloData helloData = new HelloData();
        helloData.setUsername("admin");
        helloData.setAge(20);
        return helloData;
    }
}
