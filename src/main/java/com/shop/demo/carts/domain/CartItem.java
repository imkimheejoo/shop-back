package com.shop.demo.carts.domain;

import com.shop.demo.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey)
    private Cart cart;

    @Embedded
    private CartItemInfo info;

    public CartItem(CartItemInfo cartItemInfo, Cart cart) {
        this.info = cartItemInfo;
        this.cart = cart;
    }

    public boolean isSameItem(CartItemInfo cartItemInfo) {
        return this.info.equals(cartItemInfo);
    }

    public void addCount(int itemCount) {
        this.info.addCount(itemCount);
    }
}
