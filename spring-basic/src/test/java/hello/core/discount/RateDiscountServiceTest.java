package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountServiceTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP할인은 10퍼센트가 되어야 한다")
    void vipRateDiscountPolicyTest(){
        Member vipMember = new Member(2L, "name2",Grade.VIP);

        int vipDiscount = discountPolicy.discount(vipMember, 10000);
        Assertions.assertEquals(1000,vipDiscount);
    }

    @Test
    @DisplayName("VIP할인은 10퍼센트가 되어야 한다")
    void basicRateDiscountPolicyTest(){
        Member basicMember = new Member(1L,"name1",Grade.BASIC);

        int baiscDiscount = discountPolicy.discount(basicMember, 10000);
        Assertions.assertEquals(0,baiscDiscount);
    }
}