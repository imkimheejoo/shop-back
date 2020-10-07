package com.shop.demo.domain.products;

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

    @OneToMany
    private List<ProductOption> options = new ArrayList<>();
}
