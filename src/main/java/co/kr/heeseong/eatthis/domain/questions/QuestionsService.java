package co.kr.heeseong.eatthis.domain.questions;

import co.kr.heeseong.eatthis.dto.Questions;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class QuestionsService {

    private QuestionsRepository questionsRepository;
    private static int pageSize = 10;

    public List<Questions> getQuestionsList(int page) {
        List<Questions> questionsDtoList = new ArrayList<>();
        Page<QuestionsEntity> questionsEntityList = questionsRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"idx"));

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

        return questionsDtoList;
    }
}