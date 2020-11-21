package co.kr.heeseong.eatthis.domain.user;

import co.kr.heeseong.eatthis.Enum.GenderType;
import co.kr.heeseong.eatthis.Enum.LoginResultType;
import co.kr.heeseong.eatthis.Enum.UpdateResultType;
import co.kr.heeseong.eatthis.domain.test.TestEntity;
import co.kr.heeseong.eatthis.dto.User;
import co.kr.heeseong.eatthis.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    final private UserRepository userRepository;
    final private UserDetailRepository userDetailRepository;

    /**
     * 사용자 정보 호출
     * @param idx
     * @return User
     */
    public User getUser(Long idx) {
        try{
            Optional<UserEntity> optional = userRepository.findById(idx);
            if(optional.isPresent()){
                return User.builder()
                        .idx(optional.get().getIdx())
                        .id(optional.get().getId())
                        .nickName(optional.get().getUserDetailEntity().getNickName())
                        .password("")
                        .gender(optional.get().getUserDetailEntity().getGender().getValue())
                        .birthday(optional.get().getUserDetailEntity().getBirthday())
                        .foodAlarm(optional.get().getUserDetailEntity().getFoodAlarm())
                        .eventAlarm(optional.get().getUserDetailEntity().getEventAlarm())
                        .serviceAlarm(optional.get().getUserDetailEntity().getServiceAlarm())
                        .profileImagePath(optional.get().getUserDetailEntity().getProfileImagePath())
                        .build();
            }
            return new User();
        }catch (Exception e){
            log.info("getUserResult exception {}", e.getMessage());
            return new User();
        }
    }

    /**
     * 회원가입
     * @param user
     */
    @Transactional
    public long saveUser(User user){
        if(user.getIdx() == 0){
            return this.insertUser(user);
        }else{
            return this.updateUser(user);
        }
    }

    public long insertUser(User user){
        long idx = 0;
        try{
            idx = userRepository.save(user.toEntity()).getIdx();
            if(idx > 0){
                userDetailRepository.save(user.toDetailEntity(idx));
            }
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("중복 된 아이디 입니다. -> " + user.getId());
        }

        return idx;
    }

    public long updateUser(User user) throws IllegalArgumentException{
        UserDetailEntity userDetailEntity = userDetailRepository.findById(user.getIdx()).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다. -> " + user.getIdx()));
        userDetailEntity.update(user.getProfileImagePath(), user.getNickName(), user.getBirthday(), GenderType.getGenderTypeToEnum(user.getGender()));
        return userDetailEntity.getIdx();
    }

    /**
     * 로그인 처리
     * @param user
     * @return LoginResultType
     */
    public LoginResultType loginProsess(User user) throws Exception {
        if(userRepository.findByEmailId(user.getId()) == null){
            return LoginResultType.USER_NOT_FOUND;
        }

        if(userRepository.findByIdAndPassword(user.getId(), user.getPassword()) == 0){
            return LoginResultType.INVALID_PASSWORD;
        }

        return LoginResultType.SUCCESS;
    }

    /**
     * 점심 알람 업데이트
     * @param idx
     * @param foodAlarm
     * @return
     * @throws Exception
     */
    @Transactional
    public UpdateResultType updateFoodAlarm(Long idx, char foodAlarm) throws Exception {
        UserDetailEntity userDetailEntity = userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다. -> " + idx));
        if(StringUtil.isEmpty(String.valueOf(foodAlarm)) || (!"Y".equals(String.valueOf(foodAlarm)) && !"N".equals(String.valueOf(foodAlarm)))){
            throw new IllegalArgumentException("올바른 인자가 아닙니다.");
        }
        userDetailEntity.updateFoodAlarm(foodAlarm);
        return UpdateResultType.SUCCESS;
    }

    /**
     * 이벤트 알람 업데이트
     * @param idx
     * @param eventAlarm
     * @return
     * @throws Exception
     */
    @Transactional
    public UpdateResultType updateEventAlarm(Long idx, char eventAlarm) throws Exception {
        UserDetailEntity userDetailEntity = userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다. -> " + idx));
        if(StringUtil.isEmpty(String.valueOf(eventAlarm)) || (!"Y".equals(String.valueOf(eventAlarm)) && !"N".equals(String.valueOf(eventAlarm)))){
            throw new IllegalArgumentException("올바른 인자가 아닙니다.");
        }
        userDetailEntity.updateEventAlarm(eventAlarm);
        return UpdateResultType.SUCCESS;
    }

    /**
     * 서비스 알람 업데이트
     * @param idx
     * @param serviceAlarm
     * @return
     * @throws Exception
     */
    @Transactional
    public UpdateResultType updateServiceAlarm(Long idx, char serviceAlarm) throws Exception {
        UserDetailEntity userDetailEntity = userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다. -> " + idx));
        if(StringUtil.isEmpty(String.valueOf(serviceAlarm)) || (!"Y".equals(String.valueOf(serviceAlarm)) && !"N".equals(String.valueOf(serviceAlarm)))){
            throw new IllegalArgumentException("올바른 인자가 아닙니다.");
        }
        userDetailEntity.updateServiceAlarm(serviceAlarm);
        return UpdateResultType.SUCCESS;
    }


}
