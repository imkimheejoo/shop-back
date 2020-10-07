package com.shop.demo.domain.deliveries;

import com.shop.demo.domain.accounts.Account;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Long accountId; // 회원이랑 라이프사이클이 다름
}
