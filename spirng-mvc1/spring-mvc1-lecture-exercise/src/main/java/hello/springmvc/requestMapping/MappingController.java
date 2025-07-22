package hello.springmvc.requestMapping;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("hello basic");
        return "Hello Basic";
    }
    @RequestMapping(value = "/mapping-get-v1",method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mapping get v1");
        return "Mapping Get V1";
    }
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping get v2");
        return "Mapping Get V2";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingUserId(@PathVariable("userId") String userId){
        log.info("mapping user id : {}", userId);
        return "Mapping User Id : "+userId;
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingUserIdAndOrderId(@PathVariable("userId") String userId,@PathVariable("orderId") String orderId){
        log.info("user id : {}  order id : {}", userId,orderId);
        return "userId = " + userId + ", orderId = " + orderId;
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mapping-param");
        return "mapping-param";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mapping-header");
        return "mapping-header";
    }

    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsume(){
        log.info("mapping-consume");
        return "mapping-consume";
    }

    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces(){
        log.info("mapping-produce");
        return "mapping-produce";
    }
}
