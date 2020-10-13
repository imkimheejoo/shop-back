package com.shop.demo.orders.controller;

import com.shop.demo.accounts.domain.Account;
import com.shop.demo.dto.OrderInfo;
import com.shop.demo.orders.service.command.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity order(@RequestBody OrderInfo orderInfo) {
        return new ResponseEntity(new OrderId(orderService.saveOrderProduct(orderInfo, 1L)), HttpStatus.OK);
    }
}
