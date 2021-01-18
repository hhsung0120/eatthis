package co.kr.heeseong.eatthis.mapper;

import co.kr.heeseong.eatthis.model.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<Review> selectReviewList(long userIdx);
}
