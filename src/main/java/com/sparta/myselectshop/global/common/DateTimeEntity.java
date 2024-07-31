package com.sparta.myselectshop.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class DateTimeEntity {
    @CreatedDate
    @Column(name = "created_datetime")
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name = "modified_datetime")
    private LocalDateTime modifiedDateTime;
}
