package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void finAllBean() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountPolicyService.class);

        DiscountPolicyService discountPolicyService = ac.getBean(DiscountPolicyService.class);

        MemberService memberService = ac.getBean(MemberService.class);

        Member member = new Member(1L, "UserA", Grade.VIP);

        int discount = discountPolicyService.discount(member, 10000, "fixDiscountPolicy");

        Assertions.assertThat(discount).isEqualTo(1000);


    }

    static class DiscountPolicyService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<Dictionary> policies;

        @Autowired
        public DiscountPolicyService(Map<String, DiscountPolicy> policyMap, List<Dictionary> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
        }

        public int discount(Member member, int price, String discountCode){
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }


    }

}
