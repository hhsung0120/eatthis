package co.kr.heeseong.eatthis.service.entity;


import co.kr.heeseong.eatthis.service.entity.common.TimeEntity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SecessionEntity extends TimeEntity {

    @Id
    private long idx;

    @Column
    private String reason;
}
