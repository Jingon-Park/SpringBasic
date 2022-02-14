package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    /**
     *현제 Appconfig는 역할과 구현이 구분이 가질 않는다.
     *     public MemberServiceImpl memberService() {
     *         return new MemberServiceImpl(new MemoryMemberRepository());
     *     }
     *
     *      public OrderServiceImpl orderService() {
     *         return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
     *     }
     */

    @Bean
    public MemberService memberService() {

        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
// return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
