package co.kr.eatthis.faq.service;

import co.kr.eatthis.faq.domain.entity.FaqCategoryEntity;
import co.kr.eatthis.faq.domain.entity.FaqEntity;
import co.kr.eatthis.faq.domain.model.Faq;
import co.kr.eatthis.faq.domain.model.FaqCategory;
import co.kr.eatthis.faq.domain.repository.FaqCategoryRepository;
import co.kr.eatthis.faq.domain.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;
    private final FaqCategoryRepository faqCategoryRepository;
    private static int pageSize = 10;

    @Transactional
    public List<Faq> getFaqList(int page) {
        Page<FaqEntity> faqEntityList = faqRepository.findAll(PageRequest.of((page - 1), pageSize, Sort.Direction.DESC, "idx"));
        return new Faq().entityToVoList(faqEntityList);
    }

    public List<FaqCategory> getFaqCategoryList() {
        List<FaqCategoryEntity> faqCategoryList = faqCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
        return new FaqCategory().entityToVoList(faqCategoryList);
    }
}
