package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.domain.faq.FaqEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Faq {
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
    public Faq(Long idx, String categoryName, String title, String contents, LocalDateTime createDate, LocalDateTime lastModifiedDate){
        this.idx = idx;
        this.categoryName = categoryName;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}
