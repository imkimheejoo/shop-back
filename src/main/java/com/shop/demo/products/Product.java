package com.shop.demo.products;

import com.shop.demo.common.Money;
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
    private String title;

    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany
    private List<ProductOption> options = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    @Embedded
    private Money price;

}
