package com.shop.demo.orders;


import com.shop.demo.common.Money;
import com.shop.demo.coupons.AccountCoupon;
import com.shop.demo.deliveries.Delivery;
import lombok.*;
import sun.rmi.runtime.Log;

import javax.persistence.*;

@Entity
@Table(name = "orders")
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
    @OneToOne(fetch = FetchType.LAZY)
    private Delivery delivery;

    private Long couponId;

    @Embedded
    private Money price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

    public static Order order(Long ordererId, Delivery delivery, Long couponId, Money totalPrice) {
        return Order.builder()
                .ordererId(ordererId)
                .delivery(delivery)
                .couponId(couponId)
                .price(totalPrice)
                .shippingStatus(ShippingStatus.배송전)
                .orderStatus(OrderStatus.입금완료)
                .build();
    }

    @Builder
    public Order(Long ordererId, Delivery delivery, Long couponId, Money price, OrderStatus orderStatus, ShippingStatus shippingStatus) {
        this.ordererId = ordererId;
        this.delivery = delivery;
        this.couponId = couponId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.shippingStatus = shippingStatus;
    }

}
