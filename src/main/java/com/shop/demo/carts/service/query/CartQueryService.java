package com.shop.demo.carts.service.query;

import com.shop.demo.carts.repository.CartRepository;
import com.shop.demo.dto.query.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CartQueryService {
    private final CartRepository cartRepository;

    public List<CartItemDto> getCartItemsByAccount(Long accountId) {
        return cartRepository.findCartItemsByAccountId(accountId);
    }
}
