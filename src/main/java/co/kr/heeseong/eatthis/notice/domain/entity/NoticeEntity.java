package co.kr.heeseong.eatthis.notice.domain.entity;


import co.kr.heeseong.eatthis.common.domain.entity.TimeAndUserIdEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "notice")
public class NoticeEntity extends TimeAndUserIdEntity {

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
