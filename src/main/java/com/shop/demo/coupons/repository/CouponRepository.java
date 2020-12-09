package com.shop.demo.coupons.repository;

import com.shop.demo.coupons.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("select ac.coupon from AccountCoupon ac join fetch ac.coupon where ac.isUsed = false and ac.accountId = :accountId")
    List<Coupon> findUnUsedCouponByAccountId(@Param("accountId") Long accountId);
}
