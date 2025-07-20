package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void save() {
        //given
        Member member = new Member("tak",12);
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Member findedMemeber = memberRepository.findById(savedMember.getId());
        assertThat(findedMemeber).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        Member member1 = memberRepository.save(new Member("tak",12));
        Member member2 = memberRepository.save(new Member("tak",13));
        List<Member> all = memberRepository.findAll();
        assertThat(all).contains(member1);
        assertThat(all).contains(member2);
    }

}