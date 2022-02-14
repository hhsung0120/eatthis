package co.kr.heeseong.eatthis.notice.domain.entity;


import co.kr.heeseong.eatthis.common.domain.entity.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "notice")
public class NoticeEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeIdx;

    @Column
    private Long userIdx;

    @Column
    private String title;

    @Column
    private String contents;

    public NoticeEntity() {
    }

    @Builder
    public NoticeEntity(Long noticeIdx, Long userIdx, String title, String contents) {
        this.noticeIdx = noticeIdx;
        this.userIdx = userIdx;
        this.title = title;
        this.contents = contents;
    }

}
