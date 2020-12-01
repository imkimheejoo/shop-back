package com.shop.demo.common;

import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.InvalidPriceException;
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
        if (money < 0) {
            throw new InvalidPriceException(ErrorCode.INVALID_TYPE_VALUE);
        }

        this.money = money;
    }

    public Money discount(Money discount) {
        return new Money(this.money - discount.money < 0 ? 0 : this.money - discount.money);
    }
}
