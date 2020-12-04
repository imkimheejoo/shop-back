package com.shop.demo.carts.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private CartItemInfo cartItemInfo;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cartItemInfo = new CartItemInfo(6L, 7L, 1);
        cart.addCartItem(cartItemInfo);
    }

    @Test
    void addCartItem_기존아이템을_추가하는경우_기존수량에추가수량더하기() {
        CartItem cartItem = cart.addCartItem(cartItemInfo);

        assertEquals(cartItem.getInfo().getCount(), 2);
    }

    @Test
    void addCartItem_새로운아이을_추가하는경우() {
        CartItem cartItem = cart.addCartItem(new CartItemInfo(9L, 10L, 1));

        assertEquals(cartItem.getInfo().getCount(), 1);
    }

}