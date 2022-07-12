package co.kr.eatthis.common.service;


import co.kr.eatthis.common.Enum.CategoryType;
import co.kr.eatthis.common.domain.entity.CategoryEntity;
import co.kr.eatthis.common.domain.entity.CategoryRepository;
import co.kr.eatthis.common.domain.model.Category;
import co.kr.eatthis.common.util.LogUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategoryList(CategoryType categoryType){
        try{
            List<CategoryEntity> categoryEntity = categoryRepository.findByCategoryType(categoryType);
            return Category.entityToVoList(categoryEntity);
        }catch(Exception e){
            LogUtils.errorLog("getCategoryList exception", "categoryType : ", categoryType, e);
            throw new IllegalArgumentException("getCategoryList exception : " + categoryType);
        }
    }
}
