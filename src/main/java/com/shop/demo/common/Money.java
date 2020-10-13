package com.shop.demo.common;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode(of = "money")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

    private long money;

    public Money(long money) {
        this.money = money;

        if (this.money < 0) {
            this.money = 0L;
        }
    }

    public Money discount(Money discount) {
        return new Money(money - discount.money);
    }
}
