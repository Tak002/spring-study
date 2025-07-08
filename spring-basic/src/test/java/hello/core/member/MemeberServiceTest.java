package hello.core.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemeberServiceTest {
    //OCP, DIP 위반: MemberServiceImpl을 알고있어야하며, MemberService의 구현체가 변경되면 이 코드도 수정되야함
    MemberService memberService =  new MemberServiceImpl();

    @Test
    void joinTest(){
        //given
        Member newMember = new Member(1L, "name1",Grade.BASIC);

        //when
        memberService.join(newMember);
        Member findMember = memberService.findMember(newMember.getId());

        //then
        Assertions.assertEquals(newMember.getName(),findMember.getName());
    }
}
