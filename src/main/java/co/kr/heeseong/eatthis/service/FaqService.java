package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.model.Faq;
import co.kr.heeseong.eatthis.model.FaqCategory;
import co.kr.heeseong.eatthis.service.entity.FaqCategoryEntity;
import co.kr.heeseong.eatthis.service.entity.FaqEntity;
import co.kr.heeseong.eatthis.service.repository.FaqCategoryRepository;
import co.kr.heeseong.eatthis.service.repository.FaqRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class FaqService {

    private FaqRepository faqRepository;
    private FaqCategoryRepository faqCategoryRepository;
    private static int pageSize = 10;

    /**
     * 자주묻는 질문 리스트
     * @param page
     * @return List<FaqDto>
     */
    public List<Faq> getFaqList(int page) {
        List<Faq> faqDtoList = new ArrayList<>();

        Page<FaqEntity> faqEntityList = faqRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"idx"));
        for(FaqEntity faqEntity : faqEntityList){
            Faq faqDto = Faq.builder()
                    .idx(faqEntity.getIdx())
                    .title(faqEntity.getTitle())
                    .categoryName(faqEntity.getFaqCategoryEntity().getCategoryName())
                    .contents(faqEntity.getContents())
                    .createDate(faqEntity.getCreateDate())
                    .lastModifiedDate(faqEntity.getLastModifiedDate())
                    .build();

            faqDtoList.add(faqDto);
        }

        return faqDtoList;
    }

    public List<FaqCategory> getFaqCategoryList() {
        List<FaqCategoryEntity> faqCategoryList = faqCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
        List<FaqCategory> categoryList = new ArrayList<>();
        for(FaqCategoryEntity faqCategoryEntity : faqCategoryList){
            FaqCategory faqCategory = FaqCategory.builder()
                                        .idx(faqCategoryEntity.getIdx())
                                        .categoryName(faqCategoryEntity.getCategoryName())
                                        .build();
            categoryList.add(faqCategory);
        }

        return categoryList;
    }
}
