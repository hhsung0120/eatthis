package co.kr.eatthis.notice.domain.model;

import co.kr.eatthis.common.util.StringUtils;
import co.kr.eatthis.notice.domain.entity.NoticeEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@ToString
public class Notice {

    private Long seq;
    private String title;
    private String contents;
    private String createdId;
    private String createdDatetime;

    public Notice() {
        this.seq = null;
        this.title = null;
        this.contents = null;
        this.createdId = null;
        this.createdDatetime = null;
    }

    public NoticeEntity toEntity() {
        return NoticeEntity.insertForNoticeEntity()
                .seq(seq)
                .title(title)
                .contents(contents)
                .build();
    }

    public Notice(NoticeEntity noticeEntity) {
        this.seq = noticeEntity.getSeq();
        this.title = noticeEntity.getTitle();
        this.contents = noticeEntity.getContents();
        this.createdId = noticeEntity.getCreatedId();
        this.createdDatetime = StringUtils.localDateTimeToString(noticeEntity.getCreatedDatetime());
    }

    public static List<Notice> entityToList(Page<NoticeEntity> noticeEntityList){
        return noticeEntityList.getContent().stream()
                .map(entity -> new Notice(entity))
                .collect(toList());
    }

}
