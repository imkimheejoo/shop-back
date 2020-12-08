package com.shop.demo.coupons.domain;

import com.shop.demo.common.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("TP")
public class TotalPercentCoupon extends Coupon {

    @Column(nullable = false)
    @Length(min = 1, max = 100)
    private int percent;

    @Builder
    public TotalPercentCoupon(String name, @Length(min = 1, max = 100) int percent) {
        super(name);
        this.percent = percent;
    }

    @Override
    public Money discount(Money price) {
        return price.discount(percent);
    }

    @Override
    public boolean matchCouponType(Long productId) {
        return true;
    }
}
