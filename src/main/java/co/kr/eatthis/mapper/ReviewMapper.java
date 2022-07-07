package co.kr.eatthis.mapper;

import co.kr.eatthis.questions.domain.model.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<Review> selectReviewList(long userIdx);
}
