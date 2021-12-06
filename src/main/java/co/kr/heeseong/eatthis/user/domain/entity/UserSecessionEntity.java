package co.kr.heeseong.eatthis.user.domain.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "user_secession")
public class UserSecessionEntity{

    @Id
    private long userIdx;

    @Column
    private long secessionReasonIdx;

    @Column
    private String memo;

    public UserSecessionEntity() {
    }

    @Builder
    public UserSecessionEntity(long userIdx, long secessionReasonIdx, String memo) {
        this.userIdx = userIdx;
        this.secessionReasonIdx = secessionReasonIdx;
        this.memo = memo;
    }
}
