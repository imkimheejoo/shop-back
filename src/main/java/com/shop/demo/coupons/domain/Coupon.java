package com.shop.demo.coupons.domain;

import com.shop.demo.common.Money;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public abstract Money discount(Money price);
}
