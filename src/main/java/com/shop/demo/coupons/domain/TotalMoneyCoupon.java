package com.shop.demo.coupons.domain;

import com.shop.demo.common.Money;
import lombok.AccessLevel;
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

    @Override
    public Money discount(Money price) {
        return price.discount(discount);
    }
}
