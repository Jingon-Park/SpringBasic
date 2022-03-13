package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.expression.spel.CodeFlow;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.ref.Cleaner;

public class SingletoneWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        prototypeBean1.addCount();
        int prototypeBean1Count = prototypeBean1.getCount();
        prototypeBean2.addCount();
        int prototypeBean2Count = prototypeBean2.getCount();

        Assertions.assertThat(prototypeBean1Count).isEqualTo(1);
        Assertions.assertThat(prototypeBean2Count).isEqualTo(1);

        ac.close();

    }

    @Test
    void clientBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        int logic = clientBean1.logic();
        int logic2 = clientBean2.logic();

        Assertions.assertThat(logic).isEqualTo(1);

        Assertions.assertThat(logic2).isEqualTo(2);
        ac.close();


    }

    @Scope("singleton")
    static class ClientBean{
        private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
