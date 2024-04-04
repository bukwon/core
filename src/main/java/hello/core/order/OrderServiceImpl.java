package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    // final 이 붙은  필드 들을 모아 생성자를 만들어주는 어노테이션
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("OrderServiceImpl 통과");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }   /*--> RequiredArgsConstructor 가 대신 역할 해줌*/

//    private DiscountPolicy discountPolicy;  // final은 무조건 값이 할당되어야 하므로 삭제, 우선 이렇게 함으로써 DIP 는 지킴

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
