package com.shop.demo.carts.service.query;

import com.shop.demo.carts.Cart;
import com.shop.demo.carts.CartItem;
import com.shop.demo.carts.repository.CartRepository;
import com.shop.demo.dto.query.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CartQueryService {
    private final CartRepository cartRepository;

    public List<CartItemDto> getCartItemsByAccountId(Long accountId) {
        Set<CartItem> cartItems = cartRepository.findByAccountId(accountId)
                .orElse(Cart.empty())
                .getCartItems();

        return null;
    }
}
