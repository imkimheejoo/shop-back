package com.shop.demo.coupons;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
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
}
