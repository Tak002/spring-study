package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) return discountFixAmount;
        else return 0;
    }
}
