package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.claerStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring!!!!");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member,result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring!!!!");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring!!");
        repository.save(member2);

        Optional<Member> result = repository.findByName(member1.getName());
        Assertions.assertEquals(member1.getName(),result.get().getName());
    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("mem1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("mem2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertEquals(2 ,result.size());
        Assertions.assertTrue(result.contains(member1));
        Assertions.assertTrue(result.contains(member2));
    }
}
