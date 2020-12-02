package com.shop.demo.carts.controller;

import com.shop.demo.accounts.domain.LoginId;
import com.shop.demo.carts.CartItemInfo;
import com.shop.demo.carts.service.command.CartService;
import com.shop.demo.carts.service.query.CartQueryService;
import com.shop.demo.dto.query.CartItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartApiController {

    private final CartService cartService;

    @PostMapping("/item")
    public ResponseEntity addCartItem(@LoginId Long id, @RequestBody CartItemInfo cartItemInfo) {
        cartService.saveCartItem(id,cartItemInfo);
        return new ResponseEntity(HttpStatus.OK);
    }
}
