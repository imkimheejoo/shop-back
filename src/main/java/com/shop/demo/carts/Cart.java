package com.shop.demo.carts;

import com.shop.demo.accounts.domain.Account;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    /**
     * 변경에는 딱히 상관이 없는데 account 삭제되면 사실 Cart도 다 없어져야해서 orpahan.. 일단 이렇게 고
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Account account;

}
