package com.shop.demo.domain.coupons;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {
}
