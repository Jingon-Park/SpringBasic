package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanLifecycleTest {

    @Test
    void beanLifecycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifecycleConfig.class);

        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();

    }

    static class LifecycleConfig{
        @Bean
        public NetworkClient NetworkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("testUrl");
            return networkClient;
        }

    }

}
