package com.shop.demo.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemInfo {
    /**
     * 상품 정보가 바뀐들 딱히 Order에 타격 제로
     * 가격 바뀌어도 그건 Product 일이지 order는 자체적인 price 가지고 있으니까 여기 price와 노상관(변경 막음)
     */
    @Column(nullable = false)
    private Long productId;

    private Long productOptionId;

    @Column(nullable = false)
    private int count;
}
