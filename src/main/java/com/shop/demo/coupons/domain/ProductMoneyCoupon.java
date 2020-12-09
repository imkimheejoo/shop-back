package com.shop.demo.coupons.domain;

import com.shop.demo.common.Money;
import com.shop.demo.products.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("PM")
public class ProductMoneyCoupon extends Coupon{

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private Money discount;

    @Override
    public Money discount(Money price) {
        return price.discount(discount);
    }

    @Override
    public boolean matchCouponType(Long productId) {
        return product.getId().equals(productId);
    }

    @Override
    public long getDiscountInfo() {
        return discount.getMoney();
    }
}
