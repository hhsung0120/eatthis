package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.entity.UserSecessionEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Secession {
    private long idx;
    private String reason;

    public UserSecessionEntity toEntity(){
        return UserSecessionEntity.builder()
                .secessionReasonIdx(idx)
                .build();
    }
}

