package co.kr.heeseong.eatthis.model;

import lombok.*;

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
