package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    public void prototypeTest(){
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(ProtoBean.class);
        ProtoBean singleton1 = ac.getBean(ProtoBean.class);
        ProtoBean singleton2 = ac.getBean(ProtoBean.class);

        System.out.println(singleton1);
        System.out.println(singleton2);
        Assertions.assertThat(singleton1).isNotEqualTo(singleton2);
        ac.close();

        singleton1.call();
        singleton1.destroy();
    }

    @Scope("prototype")
    static class ProtoBean{
        @PostConstruct
        void init(){
            System.out.println("init");
        }
        @PreDestroy
        void destroy(){
            System.out.println("destroy");
        }

        void call(){
            System.out.println("call");
        }
    }
}
