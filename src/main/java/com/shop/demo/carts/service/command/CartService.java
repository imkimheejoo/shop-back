package com.shop.demo.carts.service.command;

import com.shop.demo.carts.domain.Cart;
import com.shop.demo.carts.domain.CartItem;
import com.shop.demo.carts.domain.CartItemInfo;
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
                .orElseGet(() -> cartRepository.save(Cart.empty(accountId)));

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
