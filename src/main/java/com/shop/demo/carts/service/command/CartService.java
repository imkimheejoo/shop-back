package com.shop.demo.carts.service.command;

import com.shop.demo.carts.domain.Cart;
import com.shop.demo.carts.domain.CartItem;
import com.shop.demo.carts.domain.CartItemInfo;
import com.shop.demo.carts.repository.CartItemRepository;
import com.shop.demo.carts.repository.CartRepository;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.NotFoundDataException;
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

        CartItem cartItem = cart.addCartItem(cartItemInfo);
        cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long accountId, Long cartItemId) {
        cartItemRepository.delete(findCartItem(accountId, cartItemId));
    }

    public void updateCartItemCount(Long accountId, Long cartItemId, int count) {
        CartItem cartItem = findCartItem(accountId, cartItemId);
        cartItem.updateCount(count);
    }

    private CartItem findCartItem(Long accountId, Long cartItemId) {
        return cartItemRepository.findByIdAndAccountId(cartItemId, accountId)
                .orElseThrow(() -> new NotFoundDataException(ErrorCode.NOT_FOUND)); // todo 날라가는 sql 확인..
    }
}
