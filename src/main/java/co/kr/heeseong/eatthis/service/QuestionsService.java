package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.ErrorCode;
import co.kr.heeseong.eatthis.Enum.EventResultType;
import co.kr.heeseong.eatthis.model.Questions;
import co.kr.heeseong.eatthis.service.entity.FaqCategoryEntity;
import co.kr.heeseong.eatthis.service.entity.QuestionsEntity;
import co.kr.heeseong.eatthis.service.repository.FaqCategoryRepository;
import co.kr.heeseong.eatthis.service.repository.QuestionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class QuestionsService {

    private QuestionsRepository questionsRepository;
    private FaqCategoryRepository faqCategoryRepository;
    private static int pageSize = 10;

    public Map<String, Object> getQuestionsList(long userIdx) {
        Map<String, Object> result = new LinkedHashMap<>();
        List<Questions> questionsDtoList = new ArrayList<>();

        int count = questionsRepository.findAllCount(userIdx);
        if(count > 0){
            List<QuestionsEntity> questionsEntityList = questionsRepository.findByUserIdx(userIdx);
            for(QuestionsEntity questionsEntity : questionsEntityList){
                FaqCategoryEntity faqCategoryEntity = faqCategoryRepository.findByCategoryIdx(questionsEntity.getCategoryIdx());
                Questions questionsDto = Questions.builder()
                        .idx(questionsEntity.getIdx())
                        .userIdx(questionsEntity.getUserIdx())
                        .questions(questionsEntity.getQuestions())
                        .answer(questionsEntity.getAnswer())
                        .status(questionsEntity.getStatus().getValue())
                        .createDate(questionsEntity.getCreateDate())
                        .categoryName(faqCategoryEntity.getCategoryName())
                        .categoryIdx(faqCategoryEntity.getIdx())
                        .lastModifiedDate(questionsEntity.getLastModifiedDate())
                        .build();

                questionsDtoList.add(questionsDto);
            }
        }
        result.put("count", count);
        result.put("list", questionsDtoList);

        return result;
    }

    public EventResultType saveQuestions(Questions questions) {
        questionsRepository.save(questions.toEntity());
        return EventResultType.SUCCESS;
    }

    /**
     * 질문 상세
     * @param questions
     * @return
     */
    public Questions getQuestions(Questions questions) {
        //TODO userIdx 세션이랑 검사해서 아니면 튕겨내기
        //questions.getUserIdx() != session.userIdx

        QuestionsEntity questionsEntity = questionsRepository.findByQuestionsIdx(questions.getIdx())
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.POST_NOT_FOUND.getValue()));

        FaqCategoryEntity faqCategoryEntity = faqCategoryRepository.findByCategoryIdx(questionsEntity.getCategoryIdx());

        return Questions.builder()
                .createDate(questionsEntity.getCreateDate())
                .status(questionsEntity.getStatus().getValue())
                .categoryName(faqCategoryEntity.getCategoryName())
                .userName("세션에 있는 이름")
                .phone(questionsEntity.getPhone())
                .email(questionsEntity.getEmail())
                .questions(questionsEntity.getQuestions())
                .answer(questionsEntity.getAnswer())
                .lastModifiedDate(questionsEntity.getLastModifiedDate())
                .build();
    }
}
