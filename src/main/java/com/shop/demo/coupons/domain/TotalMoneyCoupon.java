package com.shop.demo.coupons.domain;

import com.shop.demo.common.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("TM")
public class TotalMoneyCoupon extends Coupon{

    @Embedded
    private Money discount;

    @Builder
    public TotalMoneyCoupon(String name, int discount) {
        super(name);
        this.discount = new Money(discount);
    }

    @Override
    public Money discount(Money price) {
        return price.discount(discount);
    }

    @Override
    public boolean matchCouponType(Long productId) {
        return true;
    }
}
