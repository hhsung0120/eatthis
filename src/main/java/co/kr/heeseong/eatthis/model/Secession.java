package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.service.entity.UserSecessionEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Secession {
    private long idx;
    private long userIdx;
    private String reason;
    private String memo;

    public UserSecessionEntity toEntity(){
        return UserSecessionEntity.builder()
                .userIdx(userIdx)
                .secessionReasonIdx(idx)
                .memo(memo)
                .build();
    }
}

