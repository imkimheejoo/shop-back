package com.shop.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    /**
     * 상품 주문했는데 가격이 바뀜 -> 큰일 (가격은 바뀌는거 자체만으로 굉장한 타격임,,
     * 내가 5000원 짜리를 삿다고 생각햇는데 50000원 주문했다고 써있다,,? 그럼 전쟁인거임)
     * -> 큰일 (그래서 주문에도 가격이 필요 = 증거확보(난 이 가격으로 샀다))
     */
    @Embedded
    private Money price;
}
