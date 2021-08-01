package co.kr.heeseong.eatthis.entity;


import co.kr.heeseong.eatthis.entity.common.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@ToString
@Table(name = "faq")
public class FaqEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //외래키를 여기서 가지고 있어서 여기가 주인
    //mappedBy = 연관관계 주인은 FaqEntity.faqCategoryEntity 이기 때문에 faqCategoryEntity 가 mappedBy 이름
    @JoinColumn(name="category_idx")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private FaqCategoryEntity faqCategoryEntity;

    @Column
    private String title;

    @Column
    private String contents;

    public FaqEntity() {
    }

    @Builder
    public FaqEntity(Long idx, String title, String contents) {
        this.idx = idx;
        this.title = title;
        this.contents = contents;
    }


}
