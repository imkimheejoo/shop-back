package com.shop.demo.dto.query;

import com.shop.demo.products.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class ProductInfoDto {

    private Long id;
    private String title;
    private long price;
    private List<String> options;
    private String thumbnailUrl;

    @Builder
    public ProductInfoDto(Long id, String title, long price, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setOptions(List<OptionNameDto> dtos) {
        this.options = dtos.stream()
                .map(OptionNameDto::getOptionName)
                .collect(Collectors.toList());
    }
}

