package com.shop.demo.questions;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Question question;

}
