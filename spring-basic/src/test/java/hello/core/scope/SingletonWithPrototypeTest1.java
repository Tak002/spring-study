package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


public class SingletonWithPrototypeTest1 {
    @Test
    public void protoTypeFindTest(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        prototypeBean2.addCount();
        int count1 = prototypeBean1.getCount();
        int count2 = prototypeBean2.getCount();
        System.out.println("count1:"+count1);
        System.out.println("count2:"+count2);
    }

    @Test
    public void singletonClientUseProtoType(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int ans1 = clientBean1.logic();
        int ans2 = clientBean2.logic();
        System.out.println("ans1:"+ans1);
        System.out.println("ans2:"+ans2);
    }

    @Scope("singleton")
    static class ClientBean {

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        void init(){
            System.out.println("init PrototypeBean: "+ this);
        }

        @PreDestroy
        void destroy(){
            System.out.println("destroy PrototypeBean: "+ this);
        }
    }
}
