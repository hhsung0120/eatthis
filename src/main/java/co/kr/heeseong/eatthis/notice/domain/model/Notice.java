package co.kr.heeseong.eatthis.notice.domain.model;

import co.kr.heeseong.eatthis.notice.domain.entity.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Notice {
    private long noticeIdx;
    private long userIdx;
    private String title;
    private String contents;
    private String createDate;

    public NoticeEntity toEntity(){
        return NoticeEntity.builder()
                .noticeIdx(noticeIdx)
                .userIdx(userIdx)
                .title(title)
                .contents(contents)
                .build();
    }

    @Builder
    public Notice(Long noticeIdx, Long userIdx, String title, String contents, LocalDateTime createDate){
        this.noticeIdx = noticeIdx;
        this.userIdx = userIdx;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate == null ? "" : createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
