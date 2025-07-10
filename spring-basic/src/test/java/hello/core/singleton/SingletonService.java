package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {
    private static final SingletonService instance =  new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 외부 에서 새로운 class 객체 생성 불가
    private SingletonService() {}

    public void logic(){
        System.out.println("싱글톤 객쳊 로직 호출");
    }


}
