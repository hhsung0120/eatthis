package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.domain.notice.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Notice {
    private Long noticeIdx;
    private Long userIdx;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public NoticeEntity toEntity(){
        return NoticeEntity.builder()
                .noticeIdx(noticeIdx)
                .userIdx(userIdx)
                .title(title)
                .contents(contents)
                .build();
    }

    @Builder
    public Notice(Long noticeIdx, Long userIdx, String title, String contents, LocalDateTime createDate, LocalDateTime lastModifiedDate){
        this.noticeIdx = noticeIdx;
        this.userIdx = userIdx;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}
