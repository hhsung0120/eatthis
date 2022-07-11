package co.kr.eatthis.common.domain.model;

import co.kr.eatthis.common.domain.entity.CategoryEntity;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class Category {

    private Long seq;
    private String categoryName;

    public Category() {
    }

    public Category(CategoryEntity categoryEntity) {
        this.seq = categoryEntity.getSeq();
        this.categoryName = categoryEntity.getCategoryName();
    }

    public static List<Category> entityToVoList(List<CategoryEntity> categoryEntities){
        return categoryEntities.stream()
                .map(list -> new Category(list))
                .collect(toList());
    }
}
