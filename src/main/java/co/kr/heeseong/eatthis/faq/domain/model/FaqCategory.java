package co.kr.heeseong.eatthis.faq.domain.model;

import co.kr.heeseong.eatthis.faq.domain.entity.FaqCategoryEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class FaqCategory {
    private Long idx;
    private String categoryName;

    public FaqCategory() {
    }

    @Builder
    public FaqCategory(Long idx, String categoryName) {
        this.idx = idx;
        this.categoryName = categoryName;
    }

    public List<FaqCategory> entityToVoList(List<FaqCategoryEntity> faqCategoryList){
        return faqCategoryList.stream()
                .map(list -> new FaqCategory(list.getIdx(), list.getCategoryName()))
                .collect(toList());
    }
}
