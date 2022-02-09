package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //private static MemberRepository memberRepository = new MemoryMemberRepository();//인터페이스와 구현체를 모두 의존하므로 DIP를 위반
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public void join(Member member) {
        memberRepository.save(member);
    }
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
