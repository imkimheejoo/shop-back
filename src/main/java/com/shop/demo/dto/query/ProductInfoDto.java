package com.shop.demo.dto.query;

import com.shop.demo.products.Product;
import com.shop.demo.products.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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

//    @Builder(builderMethodName = "builder2")
    public ProductInfoDto(Long id, String title, long price, List<String> options, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.options = options;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static ProductInfoDto toDto(Product product) {
        List<String> options = product.getOptions() // 2번째 쿼리
                .stream()
                .map(ProductOption::getOptionName)
                .collect(Collectors.toList());

        return new ProductInfoDto(product.getId(), product.getTitle(), product.getPrice().getMoney(), options, product.getImageUrl());
    }

    public void setOptions(List<OptionNameDto> dtos) {
        this.options = dtos.stream()
                .map(OptionNameDto::getOptionName)
                .collect(Collectors.toList());
    }
}

