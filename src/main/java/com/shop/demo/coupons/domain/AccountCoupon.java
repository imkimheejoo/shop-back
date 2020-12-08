package com.shop.demo.coupons.domain;

import com.shop.demo.common.Money;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.InvalidInputException;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode(of = {"id"})
public class AccountCoupon {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Coupon coupon;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private boolean isUsed;

    @Builder
    public AccountCoupon(Coupon coupon, Long accountId, boolean isUsed) {
        this.coupon = coupon;
        this.accountId = accountId;
        this.isUsed = isUsed;
    }

    public void checkApplicableCoupon(Long productId, Long accountId) {
        if(!this.accountId.equals(accountId)) {
            throw new InvalidInputException((ErrorCode.ACCESS_DENIED));
        }

        if(isUsed) {
            throw new InvalidInputException(ErrorCode.INVALID_TYPE_VALUE);
        }

        if(coupon.matchCouponType(productId)) {
            throw new InvalidInputException(ErrorCode.INVALID_TYPE_VALUE);
        }
    }
}
