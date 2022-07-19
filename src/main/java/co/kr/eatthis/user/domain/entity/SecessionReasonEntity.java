package co.kr.eatthis.user.domain.entity;


import co.kr.eatthis.common.domain.entity.TimeAndUserIdEntity;
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
public class SecessionReasonEntity extends TimeAndUserIdEntity {

    @Id
    private Long seq;

    @Column
    private String reason;

    @Column
    private String useYn;

    @Column
    private int orderNumber;

    public SecessionReasonEntity() {
    }
}
