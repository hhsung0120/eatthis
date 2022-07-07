package co.kr.eatthis.notice.domain.entity;


import co.kr.eatthis.common.domain.entity.TimeAndUserIdEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "notices")
public class NoticeEntity extends TimeAndUserIdEntity {

    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column
    private String title;

    @Column
    private String contents;

    public NoticeEntity() {
    }

    @Builder(builderClassName = "insertForNoticeEntity", builderMethodName = "insertForNoticeEntity")
    public NoticeEntity(Long seq, String title, String contents) {
        this.seq = seq;
        this.title = title;
        this.contents = contents;
    }

}
