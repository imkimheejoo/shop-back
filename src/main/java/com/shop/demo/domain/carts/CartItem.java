package com.shop.demo.domain.carts;

import com.shop.demo.common.ItemInfo;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @Embedded
    private ItemInfo itemInfo;

}
