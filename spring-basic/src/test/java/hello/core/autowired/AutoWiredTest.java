package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.Native;
import java.util.Optional;

public class AutoWiredTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestAutoConfig.class);
    @Test
    @DisplayName("autoWiredTest")
    void autoWiredTest() {
    }

    @Configuration
    @ComponentScan
    public static class TestAutoConfig{
        @Autowired(required = false)
        public void setBean1(Member member){
            System.out.println("setBean1 member");
        }

        @Autowired
        public void setBean2(@Nullable Member member){
            System.out.println("setBean2 member");
            System.out.println(member);

        }

        @Autowired
        public void setBean3(Optional<Member> member){
            System.out.println("setBean3 member");
            System.out.println(member);
        }
    }


}

