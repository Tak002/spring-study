package hello.login.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = new MemberRepository();

    @AfterEach
    void clearRepository() {
        memberRepository.clear();
    }
    @Test
    void save() {
        Member member =new Member("login1", "spring1", "1234");
        Member savedMember = memberRepository.save(member);
        assertThat(savedMember).isEqualTo(member);
    }

    @Test
    void findById() {
        Member member =new Member("login1", "spring1", "1234");
        Member savedMember = memberRepository.save(member);
        Member foundMember = memberRepository.findById(savedMember.getId());
        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void findByExistLoginId() {
        Member member =new Member("login1", "spring1", "1234");
        Member savedMember = memberRepository.save(member);
        Optional<Member> optionalMember = memberRepository.findByLoginId(savedMember.getLoginId());
        Member foundMember = optionalMember.get();
        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void findByNotExistLoginId() {
        Member member =new Member("login1", "spring1", "1234");
        memberRepository.save(member);

        String notExistLoginId = "notExistLoginId";
        Optional<Member> optionalMember = memberRepository.findByLoginId(notExistLoginId);
        assertThat(optionalMember).isEmpty();
    }
    @Test
    void findAll() {
        Member save = memberRepository.save(new Member("login1", "spring1", "1234"));
        Member save1 = memberRepository.save(new Member("login2", "spring2", "1234"));
        Member save2 = memberRepository.save(new Member("login3", "spring3", "1234"));
        List<Member> all = memberRepository.findAll();
        assertThat(all).containsOnly(save, save1, save2);
    }
}