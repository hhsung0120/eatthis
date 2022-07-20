package co.kr.eatthis.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailMapper {

    void updateLunchAlarmYn(Long userSeq);
}
