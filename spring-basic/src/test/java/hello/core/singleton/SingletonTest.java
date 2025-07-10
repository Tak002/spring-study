package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("순수한 DI")
    public void pureDi() {
        AppConfig appConfig = new AppConfig();
        //호출마다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        //호출마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 Test")
    public void singletonServiceTest() {
//        new SingletonService();
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance() ;
        Assertions.assertThat(instance1).isEqualTo(instance2);

    }


    @Test
    @DisplayName("Spring Container, Singleton Test")
    public void springSingletonTest() {
        ApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // memberService1 == memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

}
