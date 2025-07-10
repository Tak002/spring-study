package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemeberServiceTest {
    MemberService memberService;
    @BeforeEach
    public void setup() {
        AppConfig appconfig = new AppConfig();
        this.memberService = appconfig.memberService();
    }

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
