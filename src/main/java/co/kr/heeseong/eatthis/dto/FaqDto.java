package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.domain.faq.FaqEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FaqDto {
    private Long idx;
    private String categoryName;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public FaqEntity toEntity(){
        return FaqEntity.builder()
                .idx(idx)
                .title(title)
                .contents(contents)
                .build();
    }

    @Builder
    public FaqDto(Long idx, String categoryName, String title, String contents, LocalDateTime createDate, LocalDateTime lastModifiedDate){
        this.idx = idx;
        this.categoryName = categoryName;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}
