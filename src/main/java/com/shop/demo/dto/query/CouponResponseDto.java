package com.shop.demo.dto.query;

import com.shop.demo.coupons.domain.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponseDto {

    private Long id;
    private String name;

    public static CouponResponseDto toDto(Coupon coupon) {
        return new CouponResponseDto(coupon.getId(), coupon.getName());
    }
}
