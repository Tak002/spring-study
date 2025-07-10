package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameTypeBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입 Bean이 여러개 있으면 에러 발생")
    void findBeansBySameType(){
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입의 Bean이 여러개 있을 때 이름을 지정")
    void findBeanByType(){
        ac.getBean("memberRepository1", MemberRepository.class);
        ac.getBean("memberRepository2", MemberRepository.class);
    }

    @Test
    @DisplayName("같은 타입의 모든 Bean객체들 조회")
    void findAllBeansByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("name: "+ s + " bean: "+beansOfType.get(s));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1(){
          return  new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return  new MemoryMemberRepository();
        }
    }
}
