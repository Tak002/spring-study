package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    public void singletonTest(){
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singleton1 = ac.getBean(SingletonBean.class);
        SingletonBean singleton2 = ac.getBean(SingletonBean.class);
        Assertions.assertSame(singleton1,singleton2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        void init(){
            System.out.println("init");
        }
        @PreDestroy
        void destroy(){
            System.out.println("destroy");
        }
    }
}
