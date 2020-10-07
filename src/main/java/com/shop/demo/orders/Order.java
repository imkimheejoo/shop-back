package com.shop.demo.orders;


import com.shop.demo.common.Money;
import com.shop.demo.coupons.AccountCoupon;
import com.shop.demo.deliveries.Delivery;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Order가 바뀐다해서 그걸 산사람이 뾰로롱하고 바뀌는 건 아니고
     * Account 정보를 지지고볶아서 만든 걸로 Order가 뭘 할 거는 없어보임!
     */
    @Column(nullable = false)
    private Long ordererId;

    /**
     * todo
     * 배송지를 변경...?? 벼어어어언경어어어엉??? 주문 끝난거면 배송지 고정인데..
     * 주문끝나고 배송지 바꾸고 주문취소하면 어쩌지..
     * 너무 오버엔지니어링인가..?
     */
    @OneToOne(fetch = FetchType.EAGER)
    private Delivery delivery;

    /**
     * todo
     * 일단 할말은 많지만 Pass...
     */
    @OneToOne(fetch = FetchType.EAGER)
    private AccountCoupon coupon;

    @Embedded
    private Money price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;
}
