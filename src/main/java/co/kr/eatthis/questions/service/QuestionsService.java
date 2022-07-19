package co.kr.eatthis.questions.service;

import co.kr.eatthis.common.Enum.CategoryType;
import co.kr.eatthis.common.Enum.ErrorCode;
import co.kr.eatthis.common.domain.entity.CategoryRepository;
import co.kr.eatthis.common.domain.model.Category;
import co.kr.eatthis.common.domain.model.PageNavigator;
import co.kr.eatthis.common.util.LogUtils;
import co.kr.eatthis.questions.domain.entity.QuestionsEntity;
import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.questions.domain.repository.QuestionsRepository;
import co.kr.eatthis.questions.mapper.QuestionsMapper;
import co.kr.eatthis.user.domain.model.AccountUser;
import co.kr.eatthis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final CategoryRepository categoryRepository;

    private final UserService userService;

    private final QuestionsMapper questionsMapper;

    @Transactional
    public void saveQuestions(Questions questions) {
        log.info("saveQuestions {} ", questions.toString());

        AccountUser accountUser = userService.getAccountUser();

        try {
            questions.setUserSeq(accountUser.getUserSeq());
            questionsRepository.save(questions.toEntity());
        } catch (Exception e) {
            LogUtils.errorLog("saveQuestions exception", "saveQuestions : ", questions, e);
            throw new IllegalArgumentException("saveQuestions exception");
        }
    }

    public Map<String, Object> getQuestionList(PageNavigator pageNavigator, int categorySeq) {
        log.info("getNoticeList pageNavigator : {}, categorySeq : {}", pageNavigator, categorySeq);
        Map<String, Object> result = new LinkedHashMap<>();

        AccountUser accountUser = userService.getAccountUser();
        accountUser.setSearchSeq(categorySeq);


        try {
            int count = questionsMapper.selectQuestionListCount(accountUser);
            accountUser.setTotalCount(count, pageNavigator.getPage(), pageNavigator.getPageSize());

            if (accountUser.getTotalCount() > 0) {
                List<Questions> questionsList = questionsMapper.selectQuestionList(accountUser);
                result.put("questionsList", questionsList);
                result.put("totalCount", accountUser.getTotalCount());
                result.put("totalPageSize", accountUser.getTotalPageCount());
                result.put("categoryList", Category.entityToVoList(categoryRepository.findByCategoryType(CategoryType.QUESTIONS)));
            }

            return result;
        } catch (Exception e) {
            LogUtils.errorLog("getQuestionList exception", "accountUser", accountUser.toString(), e);
            throw new IllegalArgumentException("getQuestionList exception : " + e.getMessage());
        }
    }

    public Questions getQuestionsDetail(Long questionsIdx) {

        QuestionsEntity questionsEntity = null;
        try {
            questionsEntity =
                    Optional.ofNullable(questionsRepository.findById(questionsIdx))
                            .get()
                            .orElseGet(() -> null);

        } catch (Exception e) {
            LogUtils.errorLog("questionsEntity findById exception", "questionsIdx", questionsIdx, e);
            throw new IllegalArgumentException("getQuestionsDetail exception");
        }

        if (questionsEntity == null) {
            LogUtils.errorLog("questionsEntity == null", "questionsIdx", questionsIdx);
            throw new IllegalArgumentException(ErrorCode.POST_NOT_FOUND.getMessageKr());
        }

        userService.checkLoginUser(questionsEntity.getUserSeq());

        return new Questions(questionsEntity);
    }
}
