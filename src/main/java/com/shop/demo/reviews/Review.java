package com.shop.demo.reviews;

import com.shop.demo.common.Content;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Content content;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long authorId;

}
