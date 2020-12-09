package com.shop.demo.coupons.domain;

import com.shop.demo.common.Money;
import com.shop.demo.dto.query.CouponDto;
import com.shop.demo.dto.query.ProductCouponDto;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.InvalidInputException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Builder
    public Coupon(String name) {
        this.name = name;
    }

    public static Coupon ofTotalCoupon(CouponDto couponDto) {
        if("TM".equals(couponDto.getType())) {
            return TotalMoneyCoupon.builder()
                    .name(couponDto.getName())
                    .discount(couponDto.getValue())
                    .build();
        }

        if("TP".equals(couponDto.getType())) {
            return TotalPercentCoupon.builder()
                    .name(couponDto.getName())
                    .percent(couponDto.getValue())
                    .build();
        }

        throw new InvalidInputException(ErrorCode.INVALID_TYPE_VALUE);
    }

    public static Coupon ofProductCoupon(ProductCouponDto couponDto) {
        if("PM".equals(couponDto.getCouponDto().getType())) {
            return TotalMoneyCoupon.builder()
                    .name(couponDto.getCouponDto().getName())
                    .discount(couponDto.getCouponDto().getValue())
                    .build();
        }

        if("PP".equals(couponDto.getCouponDto().getType())) {
            return TotalMoneyCoupon.builder()
                    .name(couponDto.getCouponDto().getName())
                    .discount(couponDto.getCouponDto().getValue())
                    .build();
        }

        throw new InvalidInputException(ErrorCode.INVALID_TYPE_VALUE);
    }

    public abstract Money discount(Money price);

    public abstract boolean matchCouponType(Long productId);

    public abstract long getDiscountInfo();
}
