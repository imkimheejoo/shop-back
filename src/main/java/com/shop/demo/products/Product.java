package com.shop.demo.products;

import com.shop.demo.common.BaseTimeEntity;
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
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOption> options = new ArrayList<>();

    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Embedded
    private Money price;

    @Builder
    public Product(String title, String description, String imageUrl, String content, Category category, Money price) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.content = content;
        this.category = category;
        this.price = price;
    }
}
