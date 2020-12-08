package com.shop.demo.coupons.domain;

import com.shop.demo.common.Money;
import com.shop.demo.products.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("PP")
public class ProductPercentCoupon extends Coupon {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    @Length(min = 1, max = 100)
    private int percent;

    @Override
    public Money discount(Money price) {
        return price.discount(percent);
    }

    @Override
    public boolean matchCouponType(Long productId) {
        return product.getId().equals(productId);
    }
}
