package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {


    /**
     * 다른 저장소로 변경할때 OCP 원칙을 준수하는가?
     *
     *
     * DIP를 잘 지키고 있나?
     * MemberSericeImpl의   private static MemberRepository memberRepository = new MemoryMemberRepository(); 부분이
     * 인터페이스도 의존하고 구현체도 의존하고 있으므로 DIP를 지키지 않고 있음.
     */
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
