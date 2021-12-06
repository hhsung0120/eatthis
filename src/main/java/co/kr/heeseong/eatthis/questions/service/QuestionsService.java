package co.kr.heeseong.eatthis.questions.service;

import co.kr.heeseong.eatthis.common.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.questions.domain.entity.QuestionsEntity;
import co.kr.heeseong.eatthis.questions.domain.model.Questions;
import co.kr.heeseong.eatthis.questions.repository.QuestionsRepository;
import co.kr.heeseong.eatthis.user.service.UserService;
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

    public Map<String, Object> getQuestionsList() {
        Map<String, Object> result = new LinkedHashMap<>();
        Long userIdx = userService.getAccountUserIdx();
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
                                                            .lastModifiedDate(list.getLastModifiedDate())
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
            questions.setUserIdx(userService.getAccountUserIdx());
            questionsRepository.save(questions.toEntity());
        }catch (Exception e){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue());
        }

    }

    public Questions getQuestionsDetail(Long questionsIdx) {
        QuestionsEntity questionsEntity = questionsRepository.findById(questionsIdx)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.POST_NOT_FOUND.getValue()));

        return questionsEntity.toValueObject(userService.getAccountUser().getNickName());
    }
}
