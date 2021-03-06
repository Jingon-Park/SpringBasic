package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        /**
         * Bean lifecycle은 생성자 주입이 아닌이상, 객체 생성 -> 의존관계 주입이다.
         * 이때 초기화 작업을 의존관계 주입이 일어나지 않은 상태에서 진행하면 null이 출력되는 것을 확인했다.
         *
         * !! 초기화 => 어떤 처음 제대로 동작(일)했을때를 말한다. -> 여기서는 connect()가 초기화임.
         * */
        System.out.println("생성자 호출, url = " + url);
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + message);

    }

    public void discount() {
        System.out.println("discount : " + url);

    }


    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        discount();

    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        connect();
        call("init message");
    }
}
