package co.kr.heeseong.eatthis.common.domain.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeAndUserIdEntity {

    @Column(updatable = false, insertable = false)
    private String createdId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDatetime;

    @Column(updatable = false, insertable = false)
    private String modifiedId;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedDatetime;

    public TimeAndUserIdEntity() {
    }

    public TimeAndUserIdEntity(String createdId) {
        this.createdId = createdId;
    }
}
