package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<Long, Member>();
    private static Long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save member= {}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().
                filter(member -> member.getLoginId().equals(loginId))
                .findFirst();
    }
    public List<Member> findAll() {
        return new ArrayList<Member>(store.values());
    }

    public void clear(){
        store.clear();
    }
}
