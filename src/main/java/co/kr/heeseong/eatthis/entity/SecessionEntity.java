package co.kr.heeseong.eatthis.entity;


import co.kr.heeseong.eatthis.entity.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "secession_reason")
@ToString
public class SecessionEntity extends TimeEntity {

    @Id
    private long idx;

    @Column
    private String reason;

    @Column
    private String status;

    @Column
    private int order;

    public SecessionEntity() {
    }
}
