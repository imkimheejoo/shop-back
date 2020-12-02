package com.shop.demo.carts;

import com.shop.demo.accounts.domain.Account;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public Cart(Account account) {
        this.account = account;
    }

    /**
     * 변경에는 딱히 상관이 없는데 account 삭제되면 사실 Cart도 다 없어져야해서 orpahan.. 일단 이렇게 고
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Account account;

    public static Cart empty() {
        return new Cart();
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
}
