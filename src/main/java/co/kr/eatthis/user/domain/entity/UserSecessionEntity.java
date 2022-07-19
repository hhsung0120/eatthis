package co.kr.eatthis.user.domain.entity;


import co.kr.eatthis.common.domain.entity.TimeAndUserIdEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_secession")
public class UserSecessionEntity extends TimeAndUserIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Long userSeq;

    @Column
    private Long secessionReasonSeq;

    @Column
    private String memo;

    public UserSecessionEntity() {
    }

    @Builder(builderClassName = "insertForUserSecessionEntity", builderMethodName = "insertForUserSecessionEntity")
    public UserSecessionEntity(Long userSeq, Long secessionReasonSeq, String memo) {
        super("system");
        this.userSeq = userSeq;
        this.secessionReasonSeq = secessionReasonSeq;
        this.memo = memo;
    }
}
