package hello.core.member;

public class MemberServiceImpl implements MemberService{

    /*private final MemberRepository memberRepository =
            new MemoryMemberRepository();
            MemberServiceImpl가 MemoryMemberRepository()를 직접 지정해줌
      근데 이렇게 하면 안 되니까 AppConfig에서 다 해줄 것*/

    private final MemberRepository memberRepository;

    // 생성자를 통해서 이 MemberRepository에 구현체가 뭐가 들어갈지를 config
    // 이렇게 되면 MemeberServiceImpl에는 MemberRepository라는 interface만 있음. 추상화에만 의존. DIP를 지킴.
    //
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
