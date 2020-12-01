package com.shop.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductInfo {

    private Long productId;
    private Long productOptionId;
    private int count;

}
