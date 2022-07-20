package co.kr.eatthis.faq.mapper;

import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.user.domain.model.AccountUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqsMapper {

    List<Questions> selectFaqsList(AccountUser accountUser);
    int selectFaqsListCount(AccountUser accountUser);
}
