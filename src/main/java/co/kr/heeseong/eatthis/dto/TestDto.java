package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.domain.test.TestEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TestDto {
    private Long idx;
    private String userId;
    private String userName;

    public TestEntity toEntity(){
        return TestEntity.builder()
                .idx(idx)
                .userId(userId)
                .userName(userName)
                .build();
    }

    @Builder
    public TestDto(Long idx, String userId, String userName){
        this.idx = idx;
        this.userId = userId;
        this.userName = userName;
    }
}
