package com.shop.demo.dto.query;

import com.shop.demo.coupons.domain.Coupon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class CouponInfoResponseDto {

    private Long id;
    private String name;
    private long discount;
    private String couponType;

    @Builder
    private CouponInfoResponseDto(Long id, String name, long discount, String couponType) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.couponType = couponType;
    }

    public static CouponInfoResponseDto toDto(Coupon coupon) {
        return CouponInfoResponseDto.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .discount(coupon.getDiscountInfo())
                .couponType(coupon.getType().name())
                .build();
    }
}
