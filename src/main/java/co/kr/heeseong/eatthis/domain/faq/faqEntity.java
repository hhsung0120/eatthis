package co.kr.heeseong.eatthis.domain.faq;


import co.kr.heeseong.eatthis.domain.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "faq")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class faqEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeIdx;

    @Column
    private Long userIdx;

    @Column
    private String title;

    @Column
    private String contents;

    @Builder
    public faqEntity(Long noticeIdx, Long userIdx, String title, String contents) {
        this.noticeIdx = noticeIdx;
        this.userIdx = userIdx;
        this.title = title;
        this.contents = contents;
    }
}
