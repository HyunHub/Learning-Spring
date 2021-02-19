package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

/*    private final MemberRepository memberRepository =
            new MemoryMemberRepository();*/

   // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
   // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*=> 이렇게 되면 OrderServiceImpl는 DiscountPolicy(추상.인터페이스)에도 의존하고 있고
        FixDiscountPolicy RateDiscountPolicy (구체.구현 클래스)에도 의존하고 있어서
        DIP를 위반하는 것. 구체에 의존하지 말고 항상 추상에 의존하라

        또한 RateDiscountPolicy로 변경하는 순간 소스코드를 수정해야 함.
        OCP 위반임.
        */

/*    이렇게 하면 interface에만 의존
    private DiscountPolicy discountPolicy;*/

    //AppConfig에서 해주기 위해서. final은 값을 할당받든 생성자를 만들든 해야 함
    //구체적인 class에 대해서 전혀모르고 interface에만 의존함. DIP 지킴.
    // final - 생성될 때 정해지면 안 바뀜. 생성자 또는 초기값 넣어주는 거 아니면 값 못 바꿈.
    // 생성자 만들 때 실수로 this. 누락할 수 있음. test 짤 때까지는 문제 없는데 실행하면 문제
    // final 넣으면 오류가 나서 알 수 있음.
    public final MemberRepository memberRepository;
    public final DiscountPolicy discountPolicy;

/*  // @RequiredArgsConstructor 가 이 생성자를 만들어줌. - Lombok
    @Autowired // 생성자 하나면 @Autowired 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

/*
    // Qualifier로 사용할 Bean 지정해줄 수 있음. 같은 Qualifier 찾아서 주입시켜줌
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 단일 체계 명령이 잘 된 것. order는 discount 부분은 모름. discount만 수정

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
