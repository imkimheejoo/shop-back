package com.shop.demo.coupons.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AccountCouponRepositoryTest {

    @Autowired
    private AccountCouponRepository accountCouponRepository;

    @Test
    void findByIdWithCoupon() {
        AccountCoupon accountCoupon = accountCouponRepository.findByIdWithCoupons(4L).get();
        assertNotNull(accountCoupon);
    }
}