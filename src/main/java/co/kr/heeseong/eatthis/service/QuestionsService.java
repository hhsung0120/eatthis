package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.entity.FaqCategoryEntity;
import co.kr.heeseong.eatthis.entity.QuestionsEntity;
import co.kr.heeseong.eatthis.model.Questions;
import co.kr.heeseong.eatthis.repository.FaqCategoryRepository;
import co.kr.heeseong.eatthis.repository.QuestionsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final FaqCategoryRepository faqCategoryRepository;

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

    public void saveQuestions(Questions questions) {
        try{
            questionsRepository.save(questions.toEntity());
        }catch (Exception e){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue());
        }

    }

    /**
     * 질문 상세
     * @param questions
     * @return
     */
    public Questions getQuestions(Questions questions) {
        QuestionsEntity questionsEntity = questionsRepository.findById(questions.getIdx())
                .orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.POST_NOT_FOUND.getValue()));

        return Questions.builder()
                .createDate(questionsEntity.getCreateDate())
                .status(questionsEntity.getStatus().getValue())
                .categoryName(questionsEntity.getFaqCategoryEntity().getCategoryName())
                .userName("세션에 있는 이름")
                .phone(questionsEntity.getPhone())
                .email(questionsEntity.getEmail())
                .questions(questionsEntity.getQuestions())
                .answer(questionsEntity.getAnswer())
                .lastModifiedDate(questionsEntity.getLastModifiedDate())
                .build();
    }
}
