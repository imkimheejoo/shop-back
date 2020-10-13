package com.shop.demo.coupons.repository;

import com.shop.demo.coupons.AccountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountCouponRepository extends JpaRepository<AccountCoupon, Long> {

    @Override
    @Query("select ac from AccountCoupon ac join fetch ac.coupon")
    Optional<AccountCoupon> findById(@Param("id") Long id);
}
