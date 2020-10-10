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
