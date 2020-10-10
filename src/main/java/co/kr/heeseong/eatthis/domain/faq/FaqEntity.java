package co.kr.heeseong.eatthis.domain.faq;


import co.kr.heeseong.eatthis.domain.common.TimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "faq")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //외래키를 여기서 가지고 있어서 여기가 주인
    //mappedBy = 연관관계 주인은 FaqEntity.faqCategoryEntity 이기 때문에 faqCategoryEntity 가 mappedBy 이름
    @ManyToOne
    @JoinColumn(name="category_idx")
    private FaqCategoryEntity faqCategoryEntity;

    @Column
    private String title;

    @Column
    private String contents;

    @Builder
    public FaqEntity(Long idx, Long categoryIdx, String title, String contents) {
        this.idx = idx;
        this.title = title;
        this.contents = contents;
    }

}
