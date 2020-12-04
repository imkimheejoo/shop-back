package com.shop.demo.carts.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems = new HashSet<>();

    public Cart(Long accountId) {
        this.accountId = accountId;
    }

    private Long accountId;

    public static Cart empty(Long accountId) {
        return new Cart(accountId);
    }

    public CartItem addCartItem(CartItemInfo cartItemInfo) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.isSameItem(cartItemInfo)) {
                cartItem.addCount(cartItemInfo.getCount());
                return cartItem;
            }
        }
        CartItem newItem = new CartItem(cartItemInfo, this);
        cartItems.add(newItem);
        return newItem;
    }
}
