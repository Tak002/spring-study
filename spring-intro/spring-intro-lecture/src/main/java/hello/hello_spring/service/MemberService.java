package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class  MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }
    public Long join(Member member){
        /**
         *  회원가입시 동일 이름 존재시 거부
         */
        validateDuplicatedMember(member);
        repository.save(member);
        return member.getId();
    }

    public List<Member> findMembers(){
        return repository.findAll();
    }

    public Optional<Member> findMemberById(Long id){
        return repository.findById(id);
    }

    private void validateDuplicatedMember(Member member) {
        repository.findByName(member.getName())
                        .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 회원입니다");
                        });
    }
}
