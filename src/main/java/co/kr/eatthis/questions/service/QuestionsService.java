package co.kr.eatthis.questions.service;

import co.kr.eatthis.common.util.LogUtils;
import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.questions.domain.repository.QuestionsRepository;
import co.kr.eatthis.user.domain.model.AccountUser;
import co.kr.eatthis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        try {
            AccountUser accountUser = userService.getAccountUser();
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
}
