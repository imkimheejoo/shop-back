package com.shop.demo.coupons.repository;

import com.shop.demo.coupons.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
