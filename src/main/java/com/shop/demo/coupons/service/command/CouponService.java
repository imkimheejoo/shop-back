package com.shop.demo.coupons.service.command;

import com.shop.demo.coupons.domain.AccountCoupon;
import com.shop.demo.coupons.domain.Coupon;
import com.shop.demo.coupons.repository.AccountCouponRepository;
import com.shop.demo.coupons.repository.CouponRepository;
import com.shop.demo.dto.query.CouponDto;
import com.shop.demo.dto.query.ProductCouponDto;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CouponService {

    private final AccountCouponRepository accountCouponRepository;
    private final CouponRepository couponRepository;

    @Transactional(readOnly = true)
    public Coupon getDiscountCoupon(Long productId, Long accountId, Long accountCouponId) {
        AccountCoupon accountCoupon = accountCouponRepository.findByIdWithCoupon(accountCouponId)
                .orElseThrow(() -> new NotFoundDataException(ErrorCode.NOT_FOUND));
        accountCoupon.checkApplicableCoupon(productId, accountId);

        return accountCoupon.getCoupon();
    }

    public void addProductCoupon(List<ProductCouponDto> couponDtos) {
        List<Coupon> coupons = couponDtos.stream()
                .map(Coupon::ofProductCoupon)
                .collect(Collectors.toList());
        couponRepository.saveAll(coupons);
    }

    public void addTotalCoupon(List<CouponDto> couponDtos) {
        List<Coupon> coupons = couponDtos.stream()
                .map(Coupon::ofTotalCoupon)
                .collect(Collectors.toList());
        couponRepository.saveAll(coupons);
    }
}
