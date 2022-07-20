package co.kr.eatthis.faq.service;

import co.kr.eatthis.common.Enum.CategoryType;
import co.kr.eatthis.common.domain.model.Category;
import co.kr.eatthis.common.domain.model.PageNavigator;
import co.kr.eatthis.common.service.CategoryService;
import co.kr.eatthis.common.util.LogUtils;
import co.kr.eatthis.faq.domain.entity.FaqEntity;
import co.kr.eatthis.faq.domain.model.Faq;
import co.kr.eatthis.faq.domain.repository.FaqRepository;
import co.kr.eatthis.faq.mapper.FaqsMapper;
import co.kr.eatthis.notice.domain.entity.NoticeEntity;
import co.kr.eatthis.notice.domain.model.Notice;
import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.user.domain.model.AccountUser;
import co.kr.eatthis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaqService {

    private final UserService userService;
    private final CategoryService categoryService;
    private final FaqsMapper faqsMapper;


    public Map<String, Object> getFaqList(PageNavigator pageNavigator, int categorySeq, String searchText) {
        log.info("getFaqList pageNavigator : {}, categorySeq : {}", pageNavigator, categorySeq);
        Map<String, Object> result = new LinkedHashMap<>();

        AccountUser accountUser = userService.getAccountUser();
        accountUser.setSearchSeq(categorySeq);
        accountUser.setSearchText(searchText);

        try {
            int count = faqsMapper.selectFaqsListCount(accountUser);
            accountUser.setTotalCount(count, pageNavigator.getPage(), pageNavigator.getPageSize());

            List<Questions> faqsList = new ArrayList<>();
            if (accountUser.getTotalCount() > 0) {
                faqsList = faqsMapper.selectFaqsList(accountUser);
                result.put("totalCount", accountUser.getTotalCount());
                result.put("totalPageSize", accountUser.getTotalPageCount());
            }

            result.put("faqsList", faqsList);
            result.put("categoryList", categoryService.getCategoryList(CategoryType.FAQ));
            return result;
        } catch (Exception e) {
            LogUtils.errorLog("getFaqList exception", "accountUser", accountUser.toString(), e);
            throw new IllegalArgumentException("getFaqList exception : " + e.getMessage());
        }
    }

//    public List<FaqCategory> getFaqCategoryList() {
//        List<FaqCategory> categories = faqCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
//        return Category.entityToVoList(categories);
//    }
}
