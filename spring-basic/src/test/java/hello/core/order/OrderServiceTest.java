package hello.core.order;

import hello.core.Appconfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    Appconfig appconfig = new Appconfig();
    OrderService orderService = appconfig.orderService();
    MemberService memberService = appconfig.memberService();

    @Test
    public void createOrderTest(){
        Member member = new Member(1L, "name1", Grade.VIP);
        memberService.join(member);
        Order createdOrder = orderService.createOrder(member.getId(), "item1", 10000);
        Assertions.assertEquals(1000,createdOrder.getDiscountPrice());

    }

}
