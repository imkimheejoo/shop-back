package com.shop.demo.dto.query;

import com.shop.demo.products.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionInfo {

    private Long id;
    private String name;
    private Long additionalPrice;

    public OptionInfo(ProductOption productOption) {
        this.id = productOption.getId();
        this.name = productOption.getOptionName();
        this.additionalPrice = productOption.getOptionPrice().getMoney();
    }
}
