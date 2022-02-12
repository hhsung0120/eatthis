package co.kr.heeseong.eatthis.user.domain.model;

import co.kr.heeseong.eatthis.user.domain.entity.UserSecessionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString
public class Secession {

    private Long idx;
    @JsonIgnore
    private Long userIdx;
    private String reason;
    @JsonIgnore
    private String memo;

    public Secession() {
    }

    @Builder
    public Secession(Long idx, String reason) {
        this.idx = idx;
        this.reason = reason;
    }

    @Builder
    public Secession(Long idx, String memo, String etc) {
        this.idx = idx;
        this.memo = Optional.ofNullable(memo).orElse("");
    }

    public UserSecessionEntity toEntity() {
        return UserSecessionEntity.builder()
                .userIdx(userIdx)
                .secessionReasonIdx(idx)
                .memo(memo)
                .build();
    }

}

