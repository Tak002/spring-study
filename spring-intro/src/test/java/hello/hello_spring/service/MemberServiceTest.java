package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.repository.MemoryMemberRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * given
 * when
 * then
 */
class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.claerStore();
    }
    @Test
    void 회원가입() {
        //given
        Member member= new Member();
        member.setName("mem1");
        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals(member.getId(),saveId);
    }

    @Test
    void 중복_회원_예외(){
        //given
        Member member1= new Member();
        member1.setName("mem1");

        Member member2= new Member();
        member2.setName("mem1");
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
        member1.setName("mem1");
        memberService.join(member1);

        Member member2= new Member();
        member2.setName("mem2");
        memberService.join(member2);

        List<Member> result = memberService.findMembers();
        assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(member1));
        Assertions.assertTrue(result.contains(member1));

    }

    @Test
    void findMemberById() {
        Member member= new Member();
        member.setName("mem1");
        memberService.join(member);

        Optional<Member> result = memberService.findMemberById(member.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(member,result.get());
    }
}