package co.kr.heeseong.eatthis.entity;


import co.kr.heeseong.eatthis.entity.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public NoticeEntity(Long noticeIdx, Long userIdx, String title, String contents) {
        this.noticeIdx = noticeIdx;
        this.userIdx = userIdx;
        this.title = title;
        this.contents = contents;
    }
}
