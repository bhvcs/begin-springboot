package com.example.beginspringboot.domain.posts;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass//JPA 엔티티(!)들이 이를 상속받을 경우 BaseTimeEntity 안의 필드들을 칼럼으로 인식할 것이다.
@EntityListeners(AuditingEntityListener.class)//이 클래스에 자동으로 auditing 기능 추가, auditing: 감시
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
