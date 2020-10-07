package com.shop.demo.domain.coupons;

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

    @Column(nullable = false)
    private Long couponId;

    private boolean isUsed;
}
