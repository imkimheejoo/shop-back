package com.shop.demo.domain.orders;


import com.shop.demo.domain.accounts.Account;
import com.shop.demo.domain.coupons.Coupon;
import com.shop.demo.domain.deliveries.Delivery;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Account orderer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @Embedded
    private Money price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
