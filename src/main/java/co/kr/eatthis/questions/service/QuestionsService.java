package co.kr.eatthis.questions.service;

import co.kr.eatthis.common.domain.model.PageNavigator;
import co.kr.eatthis.common.util.LogUtils;
import co.kr.eatthis.questions.domain.entity.QuestionsEntity;
import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.questions.domain.repository.QuestionsRepository;
import co.kr.eatthis.user.domain.model.AccountUser;
import co.kr.eatthis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final UserService userService;

//    public Map<String, Object> getQuestionsList() {
//        Map<String, Object> result = new LinkedHashMap<>();
////        Long userIdx = userService.getAccountUserIdx();
//        Long userIdx = 0L;
//        int count = questionsRepository.findAllCount(userIdx);
//        if (count > 0) {
//            List<QuestionsEntity> questionsEntityList = questionsRepository.findByUserIdx(userIdx);
//            List<Questions> questionsDtoList = questionsEntityList.stream()
//                    .map(list -> Questions.builder()
//                            .idx(list.getIdx())
//                            .userIdx(list.getUserIdx())
//                            .userName("")
//                            .questions(list.getQuestions())
//                            .answer(list.getAnswer())
//                            .status(list.getStatus().getValue())
//                            //.createDate(list.getCreateDate())
////                            .categoryName(list.getFaqCategoryEntity().getCategoryName())
////                            .categoryIdx(list.getFaqCategoryEntity().getIdx())
//                           // .lastModifiedDate(list.getLastModifiedDate())
//                            .phone(list.getPhone())
//                            .email(list.getEmail())
//                            .build())
//                    .collect(toList());
//            result.put("count", count);
//            result.put("list", questionsDtoList);
//        }
//
//        return result;
//    }

    @Transactional
    public void saveQuestions(Questions questions) {

        AccountUser accountUser = userService.getAccountUser();

        try {
            questions.setUserSeq(accountUser.getUserSeq());
            questionsRepository.save(questions.toEntity());
        } catch (Exception e) {
            LogUtils.errorLog("saveQuestions exception", "saveQuestions : ", questions, e);
            throw new IllegalArgumentException("saveQuestions exception");
        }

    }

    public Questions getQuestionsDetail(Long questionsIdx) {
//        QuestionsEntity questionsEntity = questionsRepository.findById(questionsIdx)
//                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.POST_NOT_FOUND.getValue()));

//        return questionsEntity.toValueObject("");
        return new Questions();
    }

    public Map<String, Object> getQuestionList(int page, int pageSize) {
        log.info("getNoticeList page : {}, pageSize : {}", page, pageSize);

        page = (page -1);
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.Direction.DESC, "createdDatetime");
        AccountUser accountUser = userService.getAccountUser();

        try{
            Map<String, Object> result = new LinkedHashMap<>();

            int count = questionsRepository.findAllCount(accountUser.getUserSeq());
            if(count > 0){
                PageNavigator pageNavigator = new PageNavigator(count);
                questionsRepository.findByUserSeq(accountUser.getUserSeq(), pageNavigator.getStartIndex(), pageNavigator.getPageSize());
            }

            return result;
        }catch (Exception e){
            LogUtils.errorLog("getQuestionList exception", "accountUser", accountUser.toString(), e);
            throw new IllegalArgumentException("getQuestionList exception : " + e.getMessage());
        }
    }
}
