package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.EventResultType;
import co.kr.heeseong.eatthis.model.Questions;
import co.kr.heeseong.eatthis.service.entity.QuestionsEntity;
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
    private static int pageSize = 10;

    public Map<String, Object> getQuestionsList(long userIdx) {
        Map<String, Object> result = new LinkedHashMap<>();
        List<Questions> questionsDtoList = new ArrayList<>();

        int count = questionsRepository.findAllCount(userIdx);
        if(count > 0){
            List<QuestionsEntity> questionsEntityList = questionsRepository.findByUserIdx(userIdx);
            for(QuestionsEntity questionsEntity : questionsEntityList){
                Questions questionsDto = Questions.builder()
                        .idx(questionsEntity.getIdx())
                        .categoryName(questionsEntity.getFaqCategoryEntity().getCategoryName())
                        .questions(questionsEntity.getQuestions())
                        .answer(questionsEntity.getAnswer())
                        .status(questionsEntity.getStatus().getValue())
                        .createDate(questionsEntity.getCreateDate())
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
}
