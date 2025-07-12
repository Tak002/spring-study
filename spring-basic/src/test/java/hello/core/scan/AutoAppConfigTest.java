package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Objects;

public class AutoAppConfigTest {
    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        Object memberService = ac.getBean(MemberService.class);
//        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
//        Map<String, MemberService> beansOfType = ac.getBeansOfType(MemberService.class);
//        for (String key : beansOfType.keySet()) {
//            BeanDefinition beanDefinition = ac.getBeanDefinition(key);
//            System.out.println(beanDefinition.getBeanClassName());
//            MemberServiceImpl memberServiceImpl = (MemberServiceImpl) ac.getBean(key, MemberService.class);
//            System.out.println(memberServiceImpl.getMemberRepository());
//            System.out.println(beanDefinition);
//
//        }
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
//            if(key.equals("environment") || key.equals("systemProperties")){continue;}
//            if(ac.getBeanDefinition(key).getRole() ==  BeanDefinition.ROLE_APPLICATION){
            System.out.println(key + ":" + beansOfType.get(key));
//                System.out.println(ac.getBeanDefinition(key).getClass().getName());}
//            }
        }
    }
}
