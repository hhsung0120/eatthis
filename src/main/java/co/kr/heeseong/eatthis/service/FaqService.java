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

import javax.transaction.Transactional;
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

    @Transactional
    public List<Faq> getFaqList(int page) {
        Page<FaqEntity> faqEntityList = faqRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"idx"));
        return faqEntityList.stream()
                            .map(list -> new Faq(list.getIdx(), list.getTitle(), list.getFaqCategoryEntity().getCategoryName()
                                                , list.getContents(), list.getCreateDate(), list.getLastModifiedDate()))
                            .collect(toList());
    }

    public List<FaqCategory> getFaqCategoryList() {
        List<FaqCategoryEntity> faqCategoryList = faqCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
        return faqCategoryList.stream()
                              .map(list -> new FaqCategory(list.getIdx(), list.getCategoryName()))
                              .collect(toList());
    }
}
