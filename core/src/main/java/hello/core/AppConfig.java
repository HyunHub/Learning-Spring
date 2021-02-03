package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // Application의 실제 동작에 필요한 구현 객체를 생성한다.

    // 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)시켜준다.

/*    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생성자 주입
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
    */
    // MemoryMemberRepository와  FixDiscountPolicy 역할이 보여야 하는데 전혀 모름 그래서 수정
    // new MemoryMemberRepository() ctrl alt m . return 타입은 interface 선택

    // method명을 가져오는 순간 역할이 다 드러남.

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 지금은 이걸 쓸 거야 근데 나중에 dbRepository로 바뀌면 이 코드만 바꾸면 됨
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
       // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
