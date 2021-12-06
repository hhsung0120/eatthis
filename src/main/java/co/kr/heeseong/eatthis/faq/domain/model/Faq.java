package co.kr.heeseong.eatthis.faq.domain.model;

import co.kr.heeseong.eatthis.faq.domain.entity.FaqEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Faq {
    private long idx;
    private String categoryName;
    private String title;
    private String contents;

    public Faq() {
    }

    public FaqEntity toEntity(){
        return FaqEntity.builder()
                .idx(idx)
                .title(title)
                .contents(contents)
                .build();
    }

    @Builder
    public Faq(Long idx, String categoryName, String title, String contents){
        this.idx = idx;
        this.categoryName = categoryName;
        this.title = title;
        this.contents = contents;
    }

}
