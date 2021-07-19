package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.Enum.EventResultType;
import co.kr.heeseong.eatthis.Enum.GenderType;
import co.kr.heeseong.eatthis.Enum.UserStatusType;
import co.kr.heeseong.eatthis.entity.SecessionEntity;
import co.kr.heeseong.eatthis.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.entity.UserEntity;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.User;
import co.kr.heeseong.eatthis.repository.SecessionRepository;
import co.kr.heeseong.eatthis.repository.UserDetailRepository;
import co.kr.heeseong.eatthis.repository.UserRepository;
import co.kr.heeseong.eatthis.repository.UserSecessionRepository;
import co.kr.heeseong.eatthis.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final SecessionRepository secessionRepository;
    private final UserSecessionRepository userScessionRepository;

    /**
     * 사용자 정보 호출
     * @param idx
     * @return User
     */
    public User getUsers(long idx){
        UserEntity userEntity = checkUser(idx);
        return User.builder()
                .idx(userEntity.getIdx())
                .id(userEntity.getId())
                .nickName(userEntity.getUserDetailEntity().getNickName())
                .password("")
                .gender(userEntity.getUserDetailEntity().getGender())
                .birthday(userEntity.getUserDetailEntity().getBirthday())
                .lunchAlarm(userEntity.getUserDetailEntity().getLunchAlarm())
                .lunchAlarmHour(userEntity.getUserDetailEntity().getLunchAlarmTime().toString().substring(0,2))
                .lunchAlarmMinute(userEntity.getUserDetailEntity().getLunchAlarmTime().toString().substring(3,5))
                .dinnerAlarm(userEntity.getUserDetailEntity().getDinnerAlarm())
                .dinnerAlarmHour(userEntity.getUserDetailEntity().getDinnerAlarmTime().toString().substring(0,2))
                .dinnerAlarmMinute(userEntity.getUserDetailEntity().getDinnerAlarmTime().toString().substring(3,5))
                .eventAlarm(userEntity.getUserDetailEntity().getEventAlarm())
                .serviceAlarm(userEntity.getUserDetailEntity().getServiceAlarm())
                .profileImagePath(userEntity.getUserDetailEntity().getProfileImagePath())
                .build();
    }

    public long insertUser(User user) {
        this.checkUserByEmail(user.getId());

        try{
            User data = new User(user.getId(), user.getPassword());
            Long idx = userRepository.save(data.toEntity()).getIdx();
            if(idx > 0){
                userDetailRepository.save(user.toDetailEntity(idx));
            }
            return idx;
        }catch (Exception e){
            log.info("insertUser Exception : {}", e.getMessage());
            return 0;
        }
    }

    @Transactional
    public long updateUser(User user) throws IllegalArgumentException{
        UserDetailEntity userDetailEntity = checkUserDetail(user.getIdx());
        userDetailEntity.update(user.getProfileImagePath(), user.getNickName(), user.getBirthday(), GenderType.getGenderTypeToEnum(user.getGender().getValue()));
        return userDetailEntity.getIdx();
    }

    public User loginProcess(User user){
        UserEntity userEntity = Optional.of(userRepository.findByEmailId(user.getId())).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.USER_NOT_FOUND.getValue()));
        Optional.of(userRepository.findByIdAndPassword(user.getId(), user.getPassword())).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.INVALID_PASSWORD.getValue()));
        //로그인에 성공하면 메인메뉴 데이터 넘겨줘야함
        return this.getUsers(userEntity.getIdx());
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
    public void updateLunchAlarm(Long idx, char alarmYn, String alarmTimeHour, String alarmTimeMinute) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);

        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        try{
            LocalTime alarmTime = LocalTime.of(Integer.parseInt(alarmTimeHour), Integer.parseInt(alarmTimeMinute));
            userDetailEntity.updateLunchAlarm(alarmYn, alarmTime);
        }catch (Exception e){
            log.info("updateLunchAlarm Exception {}", e.getMessage());
            throw new IllegalArgumentException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
        }
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
    public void updateDinnerAlarm(Long idx, char alarmYn, String alarmTimeHour, String alarmTimeMinute) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        try{
            LocalTime alarmTime = LocalTime.of(Integer.parseInt(alarmTimeHour), Integer.parseInt(alarmTimeMinute));
            userDetailEntity.updateDinnerAlarm(alarmYn, alarmTime);
        }catch (Exception e){
            log.info("updateDinnerAlarm Exception {}", e.getMessage());
            throw new IllegalArgumentException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
        }
    }

    /**
     * 이벤트 알람 업데이트
     * @param idx
     * @param alarmYn
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public void updateEventAlarm(Long idx, char alarmYn) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        try{
            userDetailEntity.updateEventAlarm(alarmYn);
        }catch (Exception e){
            log.info("updateEventAlarm Exception {}", e.getMessage());
            throw new IllegalArgumentException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
        }
    }

    /**
     * 서비스 알람 업데이트
     * @param idx
     * @param alarmYn
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public void updateServiceAlarm(Long idx, char alarmYn) {
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        try{
            userDetailEntity.updateServiceAlarm(alarmYn);
        }catch (Exception e){
            log.info("updateServiceAlarm Exception {}", e.getMessage());
            throw new IllegalArgumentException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
        }
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

    public UserDetailEntity checkUserDetail(Long idx){
        return userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + idx));
    }

    public UserEntity checkUser(Long idx){
        return userRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + idx));
    }

    public void checkUserByEmail(String email){
        UserEntity userEntity = userRepository.findByEmailId(email);
        if(userEntity != null){
            throw new DataIntegrityViolationException(ErrorCodeType.USER_DUPLICATE.getValue() + " -> " + email);
        }
    }
}
