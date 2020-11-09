package co.kr.heeseong.eatthis.domain.test;


import co.kr.heeseong.eatthis.domain.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "test")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userId;

    @Column
    private String userName;

    @Builder
    public TestEntity(Long idx, String userId, String userName) {
        this.idx = idx;
        this.userId = userId;
        this.userName = userName;
    }

    public void update(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }
}
