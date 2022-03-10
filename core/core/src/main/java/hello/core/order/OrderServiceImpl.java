package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    //private MemberRepository memberRepository = new MemoryMemberRepository();
    //private DiscountPolicy discountPolicy = new FixDiscountPolicy();


    /**
     * 할인 정책을 바꾸기 위해서 클라이어트인 OrderServiceImpl을 수정해야한다.
     *          추상(인터페이스)                      구체(구현)클래스
     * private DiscountPolicy discountPolicy = new FixDiscountPolicy();
     * 추상 인터페이스와 구체 클래스 모두 의존하므로 DIP를 위반하고, 클라이언트를 수정하므로 OCP도 위반
     *
     * 이를 해결하기 위해
     * private DiscountPolicy discountPolicy; 로 수정하면 된다.
     * 하지만 이때는 구현체가 없기 때문에 NP 예외가 발생한다.
     */
    //private DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
