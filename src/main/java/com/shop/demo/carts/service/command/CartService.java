package com.shop.demo.carts.service.command;

import com.shop.demo.carts.domain.Cart;
import com.shop.demo.carts.domain.CartItem;
import com.shop.demo.carts.domain.CartItemInfo;
import com.shop.demo.carts.repository.CartItemRepository;
import com.shop.demo.carts.repository.CartRepository;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.NotFoundDataException;
import com.shop.demo.error.exception.UnauthorizedCustomerException;
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
        Cart cart = cartRepository.findWithCartItemsByAccountId(accountId)
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

    public void deleteCartItem(Long accountId, Long cartItemId) {
        CartItem cartItem = findCartItem(accountId, cartItemId);
        cartItemRepository.delete(cartItem);
    }

    public void updateCartItemCount(Long accountId, Long cartItemId, int count) {
        CartItem cartItem = findCartItem(accountId, cartItemId);
        cartItem.updateCount(count);
    }

    private CartItem findCartItem(Long accountId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findWithCartById(cartItemId)
                .orElseThrow(() -> new NotFoundDataException(ErrorCode.NOT_FOUND));

        if (!cartItem.isOwner(accountId)) {
            throw new UnauthorizedCustomerException(ErrorCode.ACCESS_DENIED);
        }
        return cartItem;
    }
}
