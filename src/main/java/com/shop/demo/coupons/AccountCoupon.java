package com.shop.demo.coupons;

import com.shop.demo.common.ItemInfo;
import com.shop.demo.common.Money;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.NotMatchOrderPrice;
import com.shop.demo.error.exception.AlreadyUsedCouponException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountCoupon {

    // TODO: 2020/10/07 대대적인 공사필요
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", foreignKey = @ForeignKey)
    private Coupon coupon;

    private boolean isUsed;

    public AccountCoupon(Long accountId, Coupon coupon, boolean isUsed) {
        this.accountId = accountId;
        this.coupon = coupon;
        this.isUsed = isUsed;
    }

    public void validate(List<ItemInfo> orderProducts, Money totalPrice) {
        if (isUsed) {
            throw new AlreadyUsedCouponException(ErrorCode.ALREADY_USED_COUPON);
        }

        Money originPrice = new Money(orderProducts.stream()
                .mapToLong(op -> op.getPrice().getMoney()).sum());
        Money calculatedPrice = coupon.applyDiscount(originPrice);
        if (!calculatedPrice.equals(totalPrice)) {
            throw new NotMatchOrderPrice(ErrorCode.INVALID_COUPON);
        }
    }
}
