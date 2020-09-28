package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.domain.faq.FaqEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FaqDto {
    private Long idx;
    private Long categoryIdx;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public FaqEntity toEntity(){
        return FaqEntity.builder()
                .idx(idx)
                .categoryIdx(categoryIdx)
                .title(title)
                .contents(contents)
                .build();
    }

    @Builder
    public FaqDto(Long idx, Long categoryIdx, String title, String contents, LocalDateTime createDate, LocalDateTime lastModifiedDate){
        this.idx = idx;
        this.categoryIdx = categoryIdx;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}
