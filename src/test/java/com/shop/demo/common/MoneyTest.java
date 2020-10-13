package com.shop.demo.common;

import com.shop.demo.error.exception.InvalidPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void 생성_실패_마이너스() {
        assertThrows(InvalidPriceException.class, () -> new Money(-10L));
    }

    @Test
    void discount_작은금액에서_큰금액차감() {
        assertEquals(new Money(100).discount(new Money(200)), new Money(0));
    }
}