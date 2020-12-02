package com.shop.demo.carts.service.command;

import com.shop.demo.carts.Cart;
import com.shop.demo.carts.CartItem;
import com.shop.demo.carts.CartItemInfo;
import com.shop.demo.carts.repository.CartRepository;
import com.shop.demo.products.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public void saveCartItem(Long accountId, CartItemInfo cartItemInfo) {
        Cart cart = cartRepository.findByAccountId(accountId)
                .orElseGet(() -> cartRepository.save(Cart.empty()));

        boolean hasSameItem = cart.hasSameItem(cartItemInfo);
        if (hasSameItem) {
            cart.renewItemCount(cartItemInfo);
            return;
        }

        CartItem newItem = new CartItem(cartItemInfo, cart);
        cartItemRepository.save(newItem);
        cart.addItem(newItem);
    }
}
