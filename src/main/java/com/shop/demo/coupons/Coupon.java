package com.shop.demo.coupons;

import com.shop.demo.common.Money;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Money discount;
}
