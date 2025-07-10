package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private final float discountRate = 0.1F;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) return (int) (price * discountRate);
        else return 0;
    }
}
