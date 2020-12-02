package com.shop.demo.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemOptionDto {

    private Long optionId;
    private String optionName;
    private int count;

}
