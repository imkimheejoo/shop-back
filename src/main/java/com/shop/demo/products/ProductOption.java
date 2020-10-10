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
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey)
    private Product product;

    @Builder
    public ProductOption(String optionName, Money optionPrice, Product product) {
        this.optionName = optionName;
        this.optionPrice = optionPrice;
        this.product = product;
    }

}
