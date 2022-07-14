package co.kr.eatthis.questions.mapper;

import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.user.domain.model.AccountUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionsMapper {

    List<Questions> selectQuestionList(AccountUser accountUser);
    int selectQuestionListCount(AccountUser accountUser);
}
