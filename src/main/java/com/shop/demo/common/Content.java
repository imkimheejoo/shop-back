package com.shop.demo.common;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Getter
@Embeddable
public class Content {

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private String imageUrl;

}
