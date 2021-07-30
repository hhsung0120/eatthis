package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
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

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final SecessionRepository secessionRepository;
    private final UserSecessionRepository userScessionRepository;
    private final HttpServletRequest request;

    /**
     * 사용자 정보 호출
     * @param userIdx
     * @return User
     */
    public User getUsers(long userIdx){
        compareUserIdxAndTokenIdx(userIdx);
        UserEntity userEntity = checkUser(userIdx);
        return userEntity.toValueObject();
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
        compareUserIdxAndTokenIdx(user.getIdx());
        UserDetailEntity userDetailEntity = checkUserDetail(user.getIdx());
        userDetailEntity.update(user.getProfileImagePath(), user.getNickName(), user.getBirthday(), GenderType.getGenderTypeToEnum(user.getGender().getValue()));
        return userDetailEntity.getIdx();
    }

    public User loginProcess(User user){
        UserEntity userEntity = Optional.ofNullable(userRepository.findByEmailId(user.getId())).orElseThrow(() -> new RuntimeException(ErrorCodeType.USER_NOT_FOUND.getValue()));
        Optional.ofNullable(userRepository.findByIdAndPassword(user.getId(), user.getPassword())).orElseThrow(() -> new RuntimeException(ErrorCodeType.INVALID_PASSWORD.getValue()));
        //로그인에 성공하면 메인메뉴 데이터 넘겨줘야함
        return userEntity.toValueObject();
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
        compareUserIdxAndTokenIdx(idx);
        UserDetailEntity userDetailEntity = checkUserDetail(idx);

        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new RuntimeException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        try{
            LocalTime alarmTime = LocalTime.of(Integer.parseInt(alarmTimeHour), Integer.parseInt(alarmTimeMinute));
            userDetailEntity.updateLunchAlarm(alarmYn, alarmTime);
        }catch (Exception e){
            log.info("updateLunchAlarm Exception {}", e.getMessage());
            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
        }
    }

    /**
     * 저녁 알람 업데이트
     * @param idx
     * @param alarmYn
     * @param alarmTimeHour
     * @param alarmTimeMinute
     * @return
     * @throws RuntimeException
     */
    @Transactional
    public void updateDinnerAlarm(Long idx, char alarmYn, String alarmTimeHour, String alarmTimeMinute) {
        compareUserIdxAndTokenIdx(idx);
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new RuntimeException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        try{
            LocalTime alarmTime = LocalTime.of(Integer.parseInt(alarmTimeHour), Integer.parseInt(alarmTimeMinute));
            userDetailEntity.updateDinnerAlarm(alarmYn, alarmTime);
        }catch (Exception e){
            log.info("updateDinnerAlarm Exception {}", e.getMessage());
            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
        }
    }

    /**
     * 이벤트 알람 업데이트
     * @param idx
     * @param alarmYn
     * @return
     * @throws RuntimeException
     */
    @Transactional
    public void updateEventAlarm(Long idx, char alarmYn) {
        compareUserIdxAndTokenIdx(idx);
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new RuntimeException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        try{
            userDetailEntity.updateEventAlarm(alarmYn);
        }catch (Exception e){
            log.info("updateEventAlarm Exception {}", e.getMessage());
            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
        }
    }

    /**
     * 서비스 알람 업데이트
     * @param idx
     * @param alarmYn
     * @return
     * @throws RuntimeException
     */
    @Transactional
    public void updateServiceAlarm(Long idx, char alarmYn) {
        compareUserIdxAndTokenIdx(idx);
        UserDetailEntity userDetailEntity = checkUserDetail(idx);
        if(StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))){
            throw new RuntimeException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
        }

        try{
            userDetailEntity.updateServiceAlarm(alarmYn);
        }catch (Exception e){
            log.info("updateServiceAlarm Exception {}", e.getMessage());
            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
        }
    }


    public List<Secession> getSecessionReasonList() {
        List<SecessionEntity> secessionEntityList = secessionRepository.findAllForOrderNumberAsc();
        return secessionEntityList.stream()
                                    .map(list -> new Secession(list.getIdx(), list.getReason()))
                                    .collect(toList());
    }

    @Transactional
    public void updateUserStatus(Secession secession) {
        UserDetailEntity userDetailEntity = checkUserDetail(secession.getUserIdx());
        if(userScessionRepository.findByUserIdx(secession.getUserIdx()) != null){
            throw new RuntimeException(ErrorCodeType.USER_DUPLICATE.getValue() + " -> " + secession.getUserIdx());
        }

        try{
            userScessionRepository.save(secession.toEntity());
            userDetailEntity.updateStatus(UserStatusType.SECESSION);
        }catch (Exception e){
            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue());
        }
    }

    public UserDetailEntity checkUserDetail(Long idx){
        return userDetailRepository.findById(idx).orElseThrow(() -> new RuntimeException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + idx));
    }

    public UserEntity checkUser(Long idx){
        return userRepository.findById(idx).orElseThrow(() -> new RuntimeException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + idx));
    }

    public void checkUserByEmail(String email){
        UserEntity userEntity = userRepository.findByEmailId(email);
        if(userEntity != null){
            throw new DataIntegrityViolationException(ErrorCodeType.USER_DUPLICATE.getValue() + " -> " + email);
        }
    }

    private void compareUserIdxAndTokenIdx(Long userIdx) {
        Map<String, Map<String, Object>> data = (Map<String, Map<String, Object>>) request.getAttribute("accountUser");
        int idx = Integer.parseInt(data.get("data").get("idx").toString());

        if(userIdx != idx){
            throw new RuntimeException(ErrorCodeType.INVALID_REQUEST.getValue());
        }
    }
}
