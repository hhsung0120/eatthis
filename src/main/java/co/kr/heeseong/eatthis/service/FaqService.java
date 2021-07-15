package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.entity.FaqCategoryEntity;
import co.kr.heeseong.eatthis.entity.FaqEntity;
import co.kr.heeseong.eatthis.model.Faq;
import co.kr.heeseong.eatthis.model.FaqCategory;
import co.kr.heeseong.eatthis.repository.FaqCategoryRepository;
import co.kr.heeseong.eatthis.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;
    private final FaqCategoryRepository faqCategoryRepository;
    private final UserService userService;
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

    public List<FaqCategory> getFaqCategoryList(Long userIdx) {
        userService.checkUser(userIdx);

        List<FaqCategoryEntity> faqCategoryList = faqCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
        List<FaqCategory> categoryList = faqCategoryList.stream()
                                            .map(list -> new FaqCategory(list.getIdx(), list.getCategoryName()))
                                            .collect(toList());
        return categoryList;
    }
}
