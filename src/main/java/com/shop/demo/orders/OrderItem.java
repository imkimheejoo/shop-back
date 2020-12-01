package com.shop.demo.orders;

import com.shop.demo.common.ItemInfo;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Order와 OrderItem은 서로 언제나 협력해야 하는 사이 "영구적인 협력" 을 가지니까 객체참조함!
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;

    @Embedded
    private ItemInfo itemInfo;

    public OrderItem(Order order, ItemInfo itemInfo) {
        this.order = order;
        this.itemInfo = itemInfo;
    }
}
