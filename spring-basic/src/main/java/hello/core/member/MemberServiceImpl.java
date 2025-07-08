package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl() {
        //OCP, DIP 위반: MemoryMemberRepository를 알고 있어야 하며, MemoryRepository의 구현체가 바뀔 경우 이 코드도 수정 되야 함
        // 추상화(MemberRepository)에도 의존 하고, 구현체(MemoryMemberRepository)에도 의존함
        this.memberRepository = new MemoryMemberRepository();
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return  memberRepository.findById(memberId);
    }
}
