package com.shop.demo.carts.domain;

import com.shop.demo.accounts.domain.Account;
import lombok.*;

import javax.persistence.*;
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

    public boolean hasSameItem(CartItemInfo cartItemInfo) {
        return cartItems.stream()
                .anyMatch(ci -> ci.isSameItem(cartItemInfo));
    }

    public void addItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void renewItemCount(CartItemInfo cartItemInfo) {
        cartItems.stream().filter(ci ->ci.isSameItem(cartItemInfo))
                .findFirst().get().addCount(cartItemInfo.getCount());
    }

    public boolean isOwner(Long accountId) {
        return this.accountId.equals(accountId);
    }
}
