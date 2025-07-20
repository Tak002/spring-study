package hello.servlet.domain.member;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long,Member> store = new HashMap<Long,Member>();
    private static long sequence = 0L;

    @Getter
    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository() {}

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<Member>(store.values());
    }
    public Member findById(Long id) {
        return store.get(id);
    }

    public void clearStore(){
        store.clear();
    }
}
