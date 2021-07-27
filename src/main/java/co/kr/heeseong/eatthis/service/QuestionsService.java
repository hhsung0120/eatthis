package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.entity.QuestionsEntity;
import co.kr.heeseong.eatthis.model.Questions;
import co.kr.heeseong.eatthis.repository.QuestionsRepository;
import co.kr.heeseong.eatthis.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;


@Service
@Transactional
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final UserService userService;

    public Map<String, Object> getQuestionsList(long userIdx) {
        Map<String, Object> result = new LinkedHashMap<>();

        int count = questionsRepository.findAllCount(userIdx);
        if(count > 0){
            List<QuestionsEntity> questionsEntityList = questionsRepository.findByUserIdx(userIdx);
            List<Questions> questionsDtoList = questionsEntityList.stream()
                                                .map(list -> Questions.builder()
                                                            .idx(list.getIdx())
                                                            .userIdx(list.getUserIdx())
                                                            .userName(list.getUser().getUserDetailEntity().getNickName())
                                                            .questions(list.getQuestions())
                                                            .answer(list.getAnswer())
                                                            .status(list.getStatus().getValue())
                                                            .createDate(list.getCreateDate())
                                                            .categoryName(list.getFaqCategoryEntity().getCategoryName())
                                                            .categoryIdx(list.getFaqCategoryEntity().getIdx())
                                                            .lastModifiedDateToString(list.getLastModifiedDateToString(list.getLastModifiedDate()))
                                                            .phone(list.getPhone())
                                                            .email(list.getEmail())
                                                            .build())
                                                .collect(toList());
            result.put("count", count);
            result.put("list", questionsDtoList);
        }

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
        userService.checkUser(questions.getUserIdx());

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
                .lastModifiedDateToString(questionsEntity.getLastModifiedDateToString(questionsEntity.getLastModifiedDate()))
                .build();
    }
}
