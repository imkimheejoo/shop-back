package com.shop.demo.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // 이벤트를 기반으로 동작한다라는 어노테이션
@Getter
@MappedSuperclass //여기 있는 속성만 내려서 쓰는 상속관계임을 나타냄(진짜 상속관계 아님, 데이터 공유관계 정도?)
public class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;
}

