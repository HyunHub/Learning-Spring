package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
    public final MemberRepository memberRepository;
    public final DiscountPolicy discountPolicy;

    @Autowired
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
