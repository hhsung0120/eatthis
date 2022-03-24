package co.kr.heeseong.eatthis.user.domain.entity;


import co.kr.heeseong.eatthis.common.domain.entity.TimeAndUserIdEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "secession_reason")
public class SecessionEntity extends TimeAndUserIdEntity {

    @Id
    private Long idx;

    @Column
    private String reason;

    @Column
    private String status;

    @Column
    private int order;

    public SecessionEntity() {
    }
}
