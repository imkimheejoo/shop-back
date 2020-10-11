package com.shop.demo.products;

import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Category {
    상의("top"), 하의("bottom"), 아우터("outer"), 신발("shoes"), 악세사리("acc");

    private String name;

    public static Category getCategoryByName(String name) {
        return Arrays.stream(values())
                .filter(c -> c.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException(ErrorCode.INVALID_TYPE_VALUE));
    }
}
