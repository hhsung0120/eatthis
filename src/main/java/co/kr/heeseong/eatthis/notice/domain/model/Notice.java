package co.kr.heeseong.eatthis.notice.domain.model;

import co.kr.heeseong.eatthis.notice.domain.entity.NoticeEntity;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@ToString
public class Notice {

    private Long noticeIdx;
    private Long userIdx;
    private String title;
    private String contents;
    private String createDate;

    public Notice() {
    }

    public NoticeEntity toEntity() {
        return NoticeEntity.builder()
                .noticeIdx(noticeIdx)
                .userIdx(userIdx)
                .title(title)
                .contents(contents)
                .build();
    }

    @Builder
    public Notice(Long noticeIdx, Long userIdx, String title, String contents, LocalDateTime createDate) {
        this.noticeIdx = noticeIdx;
        this.userIdx = userIdx;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate == null ? "" : createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public List<Notice> entityToVoList(Page<NoticeEntity> noticeEntityList){
        return noticeEntityList.stream()
                .map(list -> Notice.builder()
                        .noticeIdx(list.getNoticeIdx())
                        .userIdx(list.getUserIdx())
                        .title(list.getTitle())
                        .contents(list.getTitle())
                        //.createDate(list.getCreateDate())
                        .build())
                .collect(toList());
    }

}