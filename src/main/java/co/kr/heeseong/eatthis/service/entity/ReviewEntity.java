package co.kr.heeseong.eatthis.service.entity;

import co.kr.heeseong.eatthis.service.entity.common.TimeEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@Entity
@Getter
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column
    private long storeIdx;

    @Column
    private long menuIdx;

    @Column
    private long userIdx;

    @Column
    private String contents;

    @Column
    private int totalPrice;

    @Column
    private float star;

    @Builder
    public ReviewEntity(long idx, long storeIdx, long menuIdx, long userIdx, String contents, int totalPrice, float star){
        this.idx = idx;
        this.storeIdx = storeIdx;
        this.menuIdx = menuIdx;
        this.userIdx = userIdx;
        this.contents = contents;
        this.totalPrice = totalPrice;
        this.star = star;
    }


}