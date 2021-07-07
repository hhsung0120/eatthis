package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.*;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.User;
import co.kr.heeseong.eatthis.entity.SecessionEntity;
import co.kr.heeseong.eatthis.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.entity.UserEntity;
import co.kr.heeseong.eatthis.repository.SecessionRepository;
import co.kr.heeseong.eatthis.repository.UserDetailRepository;
import co.kr.heeseong.eatthis.repository.UserRepository;
import co.kr.heeseong.eatthis.repository.UserSecessionRepository;
import co.kr.heeseong.eatthis.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final SecessionRepository secessionRepository;
    private final UserSecessionRepository userScessionRepository;

    /**
     * 컨트롤러 응답용
     * @param idx
     * @return
     */
    public ResponseData getUser(long idx){
        ResponseData responseData;

        try{
            responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , this.getUsers(idx));
        }catch (Exception e) {
            responseData = new ResponseData(
                    StatusCode.SERVER_ERROR.getValue()
                    , e.getMessage()
                    , new User());
        }

        return responseData;
    }

    /**
     * 사용자 정보 호출
     * @param idx
     * @return User
     */
    private User getUsers(long idx){
        UserEntity userEntity = checkUser(idx);
        return User.builder()
                .idx(userEntity.getIdx())
                .id(userEntity.getId())
                .nickName(userEntity.getUserDetailEntity().getNickName())
                .password("")
                .gender(userEntity.getUserDetailEntity().getGender().getValue())
                .birthday(userEntity.getUserDetailEntity().getBirthday())
                .lunchAlarm(userEntity.getUserDetailEntity().getLunchAlarm())
                .dinnerAlarm(userEntity.getUserDetailEntity().getDinnerAlarm())
                .eventAlarm(userEntity.getUserDetailEntity().getEventAlarm())
                .serviceAlarm(userEntity.getUserDetailEntity().getServiceAlarm())
                .profileImagePath(userEntity.getUserDetailEntity().getProfileImagePath())
                .build();
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
        long idx;
        try{
            idx = userRepository.save(user.toEntity()).getIdx();
            if(idx > 0){
                userDetailRepository.save(user.toDetailEntity(idx));
            }
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(ErrorCodeType.USER_DUPLICATE.getValue() + " -> " + user.getId());
        }

        return idx;
    }

    public long updateUser(User user) throws IllegalArgumentException{
        UserDetailEntity userDetailEntity = checkUserDetail(user.getIdx());
        userDetailEntity.update(user.getProfileImagePath(), user.getNickName(), user.getBirthday(), GenderType.getGenderTypeToEnum(user.getGender()));
        return userDetailEntity.getIdx();
    }

    /**
     * 로그인 처리
     * @param user
     * @return LoginResultType
     */
    public LoginResultType loginProsess(User user){
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
     * @param alarmYn
     * @param alarmTimeHour
     * @param alarmTimeMinute
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public EventResultType updateLunchAlarm(Long idx, char alarmYn, int alarmTimeHour, int alarmTimeMinute) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);

        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        LocalTime alarmTime = LocalTime.of(alarmTimeHour, alarmTimeMinute);
        userDetailEntity.updateLunchAlarm(alarmYn, alarmTime);

        return EventResultType.SUCCESS;
    }

    /**
     * 저녁 알람 업데이트
     * @param idx
     * @param alarmYn
     * @param alarmTimeHour
     * @param alarmTimeMinute
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public EventResultType updateDinnerAlarm(Long idx, char alarmYn, int alarmTimeHour, int alarmTimeMinute) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        LocalTime alarmTime = LocalTime.of(alarmTimeHour, alarmTimeMinute);

        userDetailEntity.updateDinnerAlarm(alarmYn, alarmTime);
        return EventResultType.SUCCESS;
    }

    /**
     * 이벤트 알람 업데이트
     * @param idx
     * @param alarmYn
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public EventResultType updateEventAlarm(Long idx, char alarmYn) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }
        userDetailEntity.updateEventAlarm(alarmYn);
        return EventResultType.SUCCESS;
    }

    /**
     * 서비스 알람 업데이트
     * @param idx
     * @param alarmYn
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public EventResultType updateServiceAlarm(Long idx, char alarmYn) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }
        userDetailEntity.updateServiceAlarm(alarmYn);
        return EventResultType.SUCCESS;
    }


    public List<Secession> getSecessionReasonList() {
        List<Secession> dataList = new ArrayList<>();
        List<SecessionEntity> secessionEntityList = secessionRepository.findAllForOrderNumberAsc();
        for(SecessionEntity secessionEntity : secessionEntityList){
            Secession secession = Secession.builder()
                                    .idx(secessionEntity.getIdx())
                                    .reason(secessionEntity.getReason())
                                    .build();

            dataList.add(secession);
        }

        return dataList;
    }

    @Transactional
    public EventResultType updateUserStatus(long idx, Secession secession) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        userScessionRepository.save(secession.toEntity());

        userDetailEntity.updateStatus(UserStatusType.SECESSION);

        return EventResultType.SUCCESS;
    }

    private UserDetailEntity checkUserDetail(Long idx){
        return userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + idx));
    }

    private UserEntity checkUser(Long idx){
        return userRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + idx));
    }
}
