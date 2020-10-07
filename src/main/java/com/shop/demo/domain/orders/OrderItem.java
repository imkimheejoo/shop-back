package com.shop.demo.domain.orders;

import com.shop.demo.common.Money;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Order와 OrderItem은 서로 언제나 협력해야 하는 사이 "영구적인 협력" 을 가지니까 객체참조함!
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

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
