package co.kr.eatthis.user.domain.model;

import co.kr.eatthis.user.domain.entity.UserSecessionEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserSecession {

    private Long seq;

    @Setter
    private Long userSeq;
    private String memo;

    public UserSecession() {
    }

    public UserSecessionEntity toEntity() {
        return UserSecessionEntity.insertForUserSecessionEntity()
                .userSeq(userSeq)
                .secessionReasonSeq(seq)
                .memo(memo)
                .build();
    }

}

