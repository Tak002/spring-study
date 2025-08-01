package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long,Member> members = new HashMap<Long,Member>();

    @Override
    public void save(Member member) {
        members.put(member.getId(), member);
    }

    @Override
    public Member findById(Long MemberId) {
        return members.get(MemberId);
    }

}
