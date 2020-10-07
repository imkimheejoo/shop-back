package com.shop.demo.domain.products;

import com.shop.demo.domain.orders.Money;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String imageUrl;

    @OneToMany
    private List<ProductOption> options = new ArrayList<>();

    @Embedded
    private Money price;

}
