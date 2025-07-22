package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {

//    Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        System.out.println("name = "+name);
        log.trace("  trace log ={}",name);
        log.warn("  warn log ={}",name);
        log.error("  error log ={}",name);
        log.debug("  debug log ={}",name);
        log.info("  info log ={}",name);
        return "logTest";
    }
}
