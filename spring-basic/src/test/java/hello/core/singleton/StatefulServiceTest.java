package hello.core.singleton;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 클래스에 state가 있을 경우 생기는 문제")
    void singletonStatefulTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
        statefulService1.order("물건1", 10000);
        statefulService2.order("물건2", 5000);
        assertNotEquals(10000,statefulService1.getPrice());
    }



    @Component
    static class SingletonConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}