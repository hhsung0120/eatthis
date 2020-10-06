package co.kr.heeseong.eatthis.domain.faq;


import co.kr.heeseong.eatthis.domain.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Table(name = "faq")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long categoryIdx;

    @Column
    private String title;

    @Column
    private String contents;

/*    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;*/

    @Builder
    public FaqEntity(Long idx, Long categoryIdx, String title, String contents) {
        this.idx = idx;
        this.categoryIdx = categoryIdx;
        this.title = title;
        this.contents = contents;
    }
}
