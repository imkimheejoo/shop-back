package com.shop.demo.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {

    // cartItem
    private Long cartItemId;

    // product
    private Long productId;
    private String productName;
    private Long productPrice;
    private String imageUrl;

    //productOption
    private CartItemOptionDto option; // todo 한 상품에 여러 옵션이 있을 경우 생각해보기 ex)슬랙스-검정,26

    public CartItemDto(Long cartItemId, Long productId, String productName, Long optionId, String optionName, int count, String imageUrl, long additionalPrice, long productPrice) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.productPrice = productPrice;
        this.option = new CartItemOptionDto(optionId, optionName, additionalPrice, count);
    }
}
