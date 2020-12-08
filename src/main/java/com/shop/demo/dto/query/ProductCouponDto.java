package com.shop.demo.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCouponDto {

    private Long productId;
    private CouponDto couponDto;
}
