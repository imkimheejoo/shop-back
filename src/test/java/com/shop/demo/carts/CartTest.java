package com.shop.demo.carts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private CartItem cartItem;
    private CartItemInfo cartItemInfo;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cartItemInfo = new CartItemInfo(6L, 7L, 1);
        cartItem = new CartItem(cartItemInfo, cart);
    }

    @Test
    void hasSameItem_중복_상품_존재_O() {
        cart.addItem(cartItem);
        assertTrue(cart.hasSameItem(cartItemInfo));
    }

    @Test
    void hasSameItem_중복_상품_존재_X() {
        assertFalse(cart.hasSameItem(cartItemInfo));
    }

    @Test
    void addItem() {
    }

    @Test
    void renewItemCount() {
        cart.addItem(cartItem);

        cart.renewItemCount(new CartItemInfo(6L, 7L, 2));

        assertTrue(cart.getCartItems().stream().anyMatch(ci -> ci.getInfo().getCount() == 3));
    }
}