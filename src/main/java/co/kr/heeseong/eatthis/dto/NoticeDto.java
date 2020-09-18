package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.domain.notice.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticeDto {
    private Long noticeIdx;
    private Long userIdx;
    private String title;
    private String contents;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public NoticeEntity toEntity(){
        return NoticeEntity.builder()
                .noticeIdx(noticeIdx)
                .userIdx(userIdx)
                .title(title)
                .contents(contents)
                .build();
    }

    @Builder
    public NoticeDto(Long noticeIdx, Long userIdx, String title, String contents, LocalDateTime regDate, LocalDateTime modDate){
        this.noticeIdx = noticeIdx;
        this.userIdx = userIdx;
        this.title = title;
        this.contents = contents;
        this.regDate = regDate;
        this.modDate = modDate;
    }

}
