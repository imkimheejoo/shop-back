package com.shop.demo.coupons.repository;

import com.shop.demo.coupons.domain.AccountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountCouponRepository extends JpaRepository<AccountCoupon, Long> {

    @Query("select ac from AccountCoupon ac join fetch ac.coupon where ac.id = :id")
    Optional<AccountCoupon> findByIdWithCoupon(@Param("id") Long id);
}
