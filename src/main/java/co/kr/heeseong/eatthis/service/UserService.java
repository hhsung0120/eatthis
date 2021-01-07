package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.*;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.User;
import co.kr.heeseong.eatthis.service.entity.SecessionEntity;
import co.kr.heeseong.eatthis.service.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.service.entity.UserEntity;
import co.kr.heeseong.eatthis.service.repository.SecessionRepository;
import co.kr.heeseong.eatthis.service.repository.UserDetailRepository;
import co.kr.heeseong.eatthis.service.repository.UserRepository;
import co.kr.heeseong.eatthis.service.repository.UserSecessionRepository;
import co.kr.heeseong.eatthis.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    final private UserRepository userRepository;
    final private UserDetailRepository userDetailRepository;
    final private SecessionRepository secessionRepository;
    final private UserSecessionRepository userScessionRepository;



    /**
     * 사용자 정보 호출
     * @param idx
     * @return User
     */
    public User getUser(long idx){
        Optional<UserEntity> optional = userRepository.findById(idx);
        if(optional.isPresent()) {
            return User.builder()
                    .idx(optional.get().getIdx())
                    .id(optional.get().getId())
                    .nickName(optional.get().getUserDetailEntity().getNickName())
                    .password("")
                    .gender(optional.get().getUserDetailEntity().getGender().getValue())
                    .birthday(optional.get().getUserDetailEntity().getBirthday())
                    .lunchAlarm(optional.get().getUserDetailEntity().getLunchAlarm())
                    .dinnerAlarm(optional.get().getUserDetailEntity().getDinnerAlarm())
                    .eventAlarm(optional.get().getUserDetailEntity().getEventAlarm())
                    .serviceAlarm(optional.get().getUserDetailEntity().getServiceAlarm())
                    .profileImagePath(optional.get().getUserDetailEntity().getProfileImagePath())
                    .build();
        }else{
            throw new IllegalArgumentException(ErrorCode.USER_NOT_FOUNT.getValue() + " -> " + idx);
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
        long idx;
        try{
            idx = userRepository.save(user.toEntity()).getIdx();
            if(idx > 0){
                userDetailRepository.save(user.toDetailEntity(idx));
            }
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(ErrorCode.USER_DUPLICATE.getValue() + " -> " + user.getId());
        }

        return idx;
    }

    public long updateUser(User user) throws IllegalArgumentException{
        UserDetailEntity userDetailEntity = userDetailRepository.findById(user.getIdx()).orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUNT.getValue() + " -> " + user.getIdx()));
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
    public EventResultType updateLunchAlarm(Long idx, char alarmYn, int alarmTimeHour, int alarmTimeMinute) throws IllegalArgumentException{
        UserDetailEntity userDetailEntity = userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUNT.getValue() + " -> " + idx));

        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCode.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
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
        UserDetailEntity userDetailEntity = userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUNT.getValue() + " -> " + idx));
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCode.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
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
        UserDetailEntity userDetailEntity = userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUNT.getValue() + " -> " + idx));
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCode.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
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
        UserDetailEntity userDetailEntity = userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUNT.getValue() + " -> " + idx));
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new IllegalArgumentException(ErrorCode.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
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
        UserDetailEntity userDetailEntity = userDetailRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUNT.getValue() + " -> " + idx));
        userScessionRepository.save(secession.toEntity());

        userDetailEntity.updateStatus(UserStatus.SECESSION);

        return EventResultType.SUCCESS;
    }
}
