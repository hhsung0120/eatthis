package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.service.entity.TestEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Test {
    private long idx;
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
    public Test(Long idx, String userId, String userName){
        this.idx = idx;
        this.userId = userId;
        this.userName = userName;
    }
}