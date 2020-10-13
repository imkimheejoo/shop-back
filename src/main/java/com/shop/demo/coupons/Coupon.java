package com.shop.demo.coupons;

import com.shop.demo.common.Money;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
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

    public Coupon(String code, String name, Money discount) {
        this.code = code;
        this.name = name;
        this.discount = discount;
    }

    public Money applyDiscount(Money price) {
        return price.discount(discount);
    }
}
