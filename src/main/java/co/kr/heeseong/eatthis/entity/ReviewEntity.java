package co.kr.heeseong.eatthis.entity;

import co.kr.heeseong.eatthis.entity.common.TimeEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@Entity
@Getter
@Table(name = "review")
public class ReviewEntity extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long storeIdx;

    @Column
    private Long menuIdx;

    @Column
    private Long userIdx;

    @Column
    private String contents;

    @Column
    private int totalPrice;

    @Column
    private float star;

    public ReviewEntity() {
    }

    @Builder
    public ReviewEntity(Long idx, Long storeIdx, Long menuIdx, Long userIdx, String contents, int totalPrice, float star) {
        this.idx = idx;
        this.storeIdx = storeIdx;
        this.menuIdx = menuIdx;
        this.userIdx = userIdx;
        this.contents = contents;
        this.totalPrice = totalPrice;
        this.star = star;
    }
}
