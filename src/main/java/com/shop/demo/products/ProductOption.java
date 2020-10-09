package com.shop.demo.products;

import com.shop.demo.common.Money;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String optionName;

    @Embedded
    private Money optionPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
