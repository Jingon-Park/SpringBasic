package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    public void autowiredOptTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(OptAppConfig.class);

    }

    static class OptAppConfig{

        @Autowired(required = false)
        public void noBean1(Member noMem) {
            System.out.println("noMem = " + noMem);
        }

        @Autowired
        public void noBean2(@Nullable Member noMem) {
            System.out.println("noMem = " + noMem);
        }

        @Autowired
        public void noBean3(Optional<Member> noMem) {
            System.out.println("noMem = " + noMem);
        }

    }
}
