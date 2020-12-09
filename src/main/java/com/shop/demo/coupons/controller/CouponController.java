package com.shop.demo.coupons.controller;

import com.shop.demo.accounts.domain.LoginId;
import com.shop.demo.coupons.service.command.CouponService;
import com.shop.demo.coupons.service.query.CouponQueryService;
import com.shop.demo.dto.query.CouponDto;
import com.shop.demo.dto.query.ProductCouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;
    private final CouponQueryService couponQueryService;

    @GetMapping
    public ResponseEntity getCustomerCoupons(@LoginId Long customerId) {
        return new ResponseEntity(couponQueryService.getCoupons(customerId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllCoupons() {
        return new ResponseEntity(couponQueryService.getAllCoupons(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addProductCoupon(@RequestBody List<ProductCouponDto> couponDtos) {
        couponService.addProductCoupon(couponDtos);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addGeneralCoupon(@RequestBody List<CouponDto> couponDtos) {
        couponService.addTotalCoupon(couponDtos);
        return new ResponseEntity(HttpStatus.OK);
    }


}
