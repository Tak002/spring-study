package hello.core.lifeCycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {

    private String url;

    NetworkClient() {
        System.out.println("NetworkClient 생성");
        }

    public void setUrl(String url) {
        this.url = url;
    }

    public void call(String message){
        System.out.println("call(): "+ url+ " message: " + message);
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect(): " + url);
    }
    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("disconnect(): " + url);
    }
    @PostConstruct
    public void init() throws Exception {
        System.out.println("init");
        connect();
        call("초기화 메시지 전달");
    }
    @PreDestroy
    public void close() throws Exception {
        System.out.println("close");
        disconnect();
    }
}
