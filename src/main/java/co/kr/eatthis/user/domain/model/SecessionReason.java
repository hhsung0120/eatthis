package co.kr.eatthis.user.domain.model;

import co.kr.eatthis.user.domain.entity.SecessionReasonEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SecessionReason {

    private Long seq;
    private String reason;

    public SecessionReason() {
    }

    public SecessionReason(SecessionReasonEntity secessionReasonEntity) {
        this.seq = secessionReasonEntity.getSeq();
        this.reason = secessionReasonEntity.getReason();
    }

    public static List<SecessionReason> entityToList(List<SecessionReasonEntity> secessionReasonEntities){
        return secessionReasonEntities.stream()
                .map(data -> new SecessionReason(data))
                .collect(Collectors.toList());
    }
}

