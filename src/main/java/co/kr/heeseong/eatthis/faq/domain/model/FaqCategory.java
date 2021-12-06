package co.kr.heeseong.eatthis.faq.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqCategory {
    private Long idx;
    private String categoryName;

    @Builder
    public FaqCategory(Long idx, String categoryName) {
        this.idx = idx;
        this.categoryName = categoryName;
    }
}
