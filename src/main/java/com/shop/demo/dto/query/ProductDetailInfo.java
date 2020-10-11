package com.shop.demo.dto.query;

import com.shop.demo.products.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductDetailInfo {

    private Long id;
    private String title;
    private String description;
    private long price;
    private List<OptionInfo> options;
    private String thumbnailUrl;
    private String content;

    @Builder
    public ProductDetailInfo(Long id, String title, String description, long price, List<OptionInfo> options, String thumbnailUrl, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.options = options;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
    }

    public static ProductDetailInfo toDto(Product product) {
        return ProductDetailInfo.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice().getMoney())
                .options(product.getOptions().stream()
                        .map(OptionInfo::new)
                        .collect(Collectors.toList()))
                .thumbnailUrl(product.getImageUrl())
                .content(product.getContent())
                .build();
    }
}
