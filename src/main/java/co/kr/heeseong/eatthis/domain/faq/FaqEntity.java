package co.kr.heeseong.eatthis.domain.faq;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "faq")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long categoryIdx;

    @Column
    private String title;

    @Column
    private String contents;
/*
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime last_modified_date;*/

    @Builder
    public FaqEntity(Long idx, Long categoryIdx, String title, String contents) {
        this.idx = idx;
        this.categoryIdx = categoryIdx;
        this.title = title;
        this.contents = contents;
    }
}
