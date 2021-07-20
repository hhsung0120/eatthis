package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.entity.UserSecessionEntity;
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
    public Secession(Long idx, Long userIdx, String memo) {
        this.idx = idx;
        this.userIdx = userIdx;
        this.memo = Optional.ofNullable(memo).orElse("");
    }

    public UserSecessionEntity toEntity(){
        return UserSecessionEntity.builder()
                .userIdx(userIdx)
                .secessionReasonIdx(idx)
                .memo(memo)
                .build();
    }

}

