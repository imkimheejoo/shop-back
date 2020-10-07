package com.shop.demo.domain.questions;

import com.shop.demo.domain.accounts.Account;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private String imageUrl;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long authorId;

    private boolean isAnswered;
}
