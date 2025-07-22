package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * given
 * when
 * then
 */
@SpringBootTest //스프링 컨테이너와 함꼐 실행
@Transactional //테스트별로 끝난 후에 db 롤백
class MemberServiceIntegTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member= new Member();
        member.setName("spring5555");
        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals(member.getId(),saveId);
    }

    @Test
    void 중복_회원_예외(){
        //given
        Member member1= new Member();
        member1.setName("mem15");

        Member member2= new Member();
        member2.setName("mem15");
        memberService.join(member1);
        IllegalStateException e=  assertThrows(IllegalStateException.class,() -> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다",e.getMessage());
//        //when
//        service.join(member1);
//        try{
//            service.join(member2);
//            fail();
//        }catch (Exception e){
//            Assertions.assertEquals("이미 존재하는 회원입니다",e.getMessage());
//        }


        //then
    }

    @Test
    void findMembers() {
        Member member1= new Member();
        member1.setName("mem20");
        memberService.join(member1);

        Member member2= new Member();
        member2.setName("mem129");
        memberService.join(member2);

        List<Member> result = memberService.findMembers();
        Assertions.assertTrue(result.contains(member1));
        Assertions.assertTrue(result.contains(member1));

    }

    @Test
    void findMemberById() {
        Member member= new Member();
        member.setName("mem99");
        memberService.join(member);

        Optional<Member> result = memberService.findMemberById(member.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(member.getName(),result.get().getName());
        Assertions.assertEquals(member.getId(),result.get().getId());
    }

}