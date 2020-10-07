package com.shop.demo.domain.products;

import com.shop.demo.domain.orders.Money;
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
    private String option;

    @Embedded
    private Money optionPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Product product;
}
