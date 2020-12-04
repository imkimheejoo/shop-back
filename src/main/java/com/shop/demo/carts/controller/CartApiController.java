package com.shop.demo.carts.controller;

import com.shop.demo.accounts.domain.LoginId;
import com.shop.demo.carts.domain.CartItemInfo;
import com.shop.demo.carts.service.command.CartService;
import com.shop.demo.carts.service.query.CartQueryService;
import com.shop.demo.dto.Count;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartApiController {

    private final CartService cartService;
    private final CartQueryService cartQueryService;

    @PostMapping("/item")
    public ResponseEntity addCartItem(@LoginId Long id, @RequestBody CartItemInfo cartItemInfo) {
        cartService.saveCartItem(id, cartItemInfo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMyCart(@LoginId Long id) {
        return new ResponseEntity(cartQueryService.getCartItemsByAccount(id), HttpStatus.OK);
    }

    @DeleteMapping("item/{cartItemId}")
    public ResponseEntity deleteCartItem(@LoginId Long id, @PathVariable Long cartItemId) {
        cartService.deleteCartItem(id, cartItemId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("item/{cartItemId}")
    public ResponseEntity updateCartItemCount(@LoginId Long id, @PathVariable Long cartItemId,
                                              @Validated @RequestBody Count count) {
        cartService.updateCartItemCount(id, cartItemId, count.getCount());
        return new ResponseEntity(HttpStatus.OK);
    }
}
