package hello.springmvc.basic.requeset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody:{}", messageBody);
        response.getWriter().write(messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        String username = helloData.getUsername();
        log.info("username:{}", username);
        Integer age = helloData.getAge();
        log.info("age:{}", age);
    }

    @ResponseBody
    @PostMapping("request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody:{}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        String username = helloData.getUsername();
        log.info("username:{}", username);

        Integer age = helloData.getAge();
        log.info("age:{}", age);

        return helloData.toString() ;
    }


    @ResponseBody
    @PostMapping("request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData){
        String username = helloData.getUsername();
        log.info("username:{}", username);

        Integer age = helloData.getAge();
        log.info("age:{}", age);

        return helloData.toString() ;
    }

    @ResponseBody
    @PostMapping("request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<String> httpEntity) throws JsonProcessingException {
        String messageBody = httpEntity.getBody();
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        String username = helloData.getUsername();
        log.info("username:{}", username);

        Integer age = helloData.getAge();
        log.info("age:{}", age);

        return helloData.toString() ;
    }

    @ResponseBody
    @PostMapping("request-body-json-v5")
    public String requestBodyJsonV5(HttpEntity<HelloData> httpEntity) throws JsonProcessingException {
        HelloData helloData = httpEntity.getBody();

        String username = helloData.getUsername();
        log.info("username:{}", username);

        Integer age = helloData.getAge();
        log.info("age:{}", age);

        return helloData.toString() ;
    }

    @ResponseBody
    @PostMapping("request-body-json-v6")
    public HelloData requestBodyJsonV6(@RequestBody HelloData helloData){
        String username = helloData.getUsername();
        log.info("username:{}", username);

        Integer age = helloData.getAge();
        log.info("age:{}", age);

        return helloData ;
    }
}
