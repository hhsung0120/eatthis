package co.kr.heeseong.eatthis.user.service;

import co.kr.heeseong.eatthis.common.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.UserStatusType;
import co.kr.heeseong.eatthis.common.util.StringUtil;
import co.kr.heeseong.eatthis.user.domain.entity.SecessionEntity;
import co.kr.heeseong.eatthis.user.domain.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.user.domain.entity.UsersEntity;
import co.kr.heeseong.eatthis.user.domain.model.AccountUser;
import co.kr.heeseong.eatthis.user.domain.model.Secession;
import co.kr.heeseong.eatthis.user.domain.repository.SecessionRepository;
import co.kr.heeseong.eatthis.user.domain.repository.UserDetailRepository;
import co.kr.heeseong.eatthis.user.domain.repository.UserRepository;
import co.kr.heeseong.eatthis.user.domain.repository.UserSecessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final UserDetailRepository userDetailRepository;
    final SecessionRepository secessionRepository;
    final UserSecessionRepository userScessionRepository;
    final HttpServletRequest request;

    public Long insertUser(AccountUser accountUser) {
        this.checkUserByEmail(accountUser.getId());

        try {
            AccountUser data = new AccountUser(accountUser.getId(), accountUser.getPassword());
            Long idx = userRepository.save(data.toEntity()).getIdx();
            if (idx > 0) {
                userDetailRepository.save(accountUser.toDetailEntity(idx));
            }
            return idx;
        } catch (Exception e) {
            log.info("insertUser Exception : {}", e.getMessage());
            return 0;
        }
    }

//    /**
//     * 사용자 정보 호출
//     *
//     * @return AccountUser
//     */
//    public AccountUser getUsers() {
//        UsersEntity userEntity = checkUser(this.getAccountUserIdx());
//        return new AccountUser(userEntity);
//    }
//

//
//    @Transactional
//    public long updateUser(AccountUser accountUser) throws IllegalArgumentException {
//        UserDetailEntity userDetailEntity = checkUserDetail(accountUser.getIdx());
//        userDetailEntity.update(accountUser.getProfileImagePath(), accountUser.getNickName(), accountUser.getBirthday(), GenderType.getGenderTypeToEnum(accountUser.getGender().getValue()));
//        return userDetailEntity.getIdx();
//    }
//
//    public AccountUser loginProcess(AccountUser accountUser) {
//        UsersEntity userEntity = Optional.ofNullable(userRepository.findByEmailId(accountUser.getId())).orElseThrow(() -> new RuntimeException(ErrorCodeType.USER_NOT_FOUND.getValue()));
//        Optional.ofNullable(userRepository.findByIdAndPassword(accountUser.getId(), accountUser.getPassword())).orElseThrow(() -> new RuntimeException(ErrorCodeType.INVALID_PASSWORD.getValue()));
//        //로그인에 성공하면 메인메뉴 데이터 넘겨줘야함
//        return new AccountUser(userEntity);
//    }
//
//    /**
//     * 점심 알람 업데이트
//     *
//     * @param alarmYn
//     * @param alarmTimeHour
//     * @param alarmTimeMinute
//     * @return
//     * @throws IllegalArgumentException
//     */
//    @Transactional
//    public void updateLunchAlarm(char alarmYn, String alarmTimeHour, String alarmTimeMinute) {
//        UserDetailEntity userDetailEntity = checkUserDetail(this.getAccountUserIdx());
//
//        if (StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))) {
//            throw new RuntimeException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
//        }
//
//        try {
//            LocalTime alarmTime = LocalTime.of(Integer.parseInt(alarmTimeHour), Integer.parseInt(alarmTimeMinute));
//            userDetailEntity.updateLunchAlarm(alarmYn, alarmTime);
//        } catch (Exception e) {
//            log.info("updateLunchAlarm Exception {}", e.getMessage());
//            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
//        }
//    }
//
//    /**
//     * 저녁 알람 업데이트
//     *
//     * @param alarmYn
//     * @param alarmTimeHour
//     * @param alarmTimeMinute
//     * @return
//     * @throws RuntimeException
//     */
//    @Transactional
//    public void updateDinnerAlarm(char alarmYn, String alarmTimeHour, String alarmTimeMinute) {
//        UserDetailEntity userDetailEntity = checkUserDetail(this.getAccountUserIdx());
//        if (StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))) {
//            throw new RuntimeException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
//        }
//
//        try {
//            LocalTime alarmTime = LocalTime.of(Integer.parseInt(alarmTimeHour), Integer.parseInt(alarmTimeMinute));
//            userDetailEntity.updateDinnerAlarm(alarmYn, alarmTime);
//        } catch (Exception e) {
//            log.info("updateDinnerAlarm Exception {}", e.getMessage());
//            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
//        }
//    }
//
//    /**
//     * 이벤트 알람 업데이트
//     *
//     * @param alarmYn
//     * @return
//     * @throws RuntimeException
//     */
//    @Transactional
//    public void updateEventAlarm(char alarmYn) {
//        UserDetailEntity userDetailEntity = checkUserDetail(this.getAccountUserIdx());
//        if (StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))) {
//            throw new RuntimeException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
//        }
//
//        try {
//            userDetailEntity.updateEventAlarm(alarmYn);
//        } catch (Exception e) {
//            log.info("updateEventAlarm Exception {}", e.getMessage());
//            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
//        }
//    }
//
//    /**
//     * 서비스 알람 업데이트
//     *
//     * @param alarmYn
//     * @return
//     * @throws RuntimeException
//     */
//    @Transactional
//    public void updateServiceAlarm(char alarmYn) {
//        UserDetailEntity userDetailEntity = checkUserDetail(this.getAccountUserIdx());
//        if (StringUtil.isEmpty(String.valueOf(alarmYn)) || (!"Y".equals(String.valueOf(alarmYn)) && !"N".equals(String.valueOf(alarmYn)))) {
//            throw new RuntimeException(ErrorCodeType.INVALID_ARGUMENT.getValue() + " -> " + alarmYn);
//        }
//
//        try {
//            userDetailEntity.updateServiceAlarm(alarmYn);
//        } catch (Exception e) {
//            log.info("updateServiceAlarm Exception {}", e.getMessage());
//            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue() + " : " + e.getMessage());
//        }
//    }
//
//
//    public List<Secession> getSecessionReasonList() {
//        List<SecessionEntity> secessionEntityList = secessionRepository.findAllForOrderNumberAsc();
//        return secessionEntityList.stream()
//                .map(list -> new Secession(list.getIdx(), list.getReason()))
//                .collect(toList());
//    }
//
//    @Transactional
//    public void updateUserStatus(Secession secession) {
//        UserDetailEntity userDetailEntity = checkUserDetail(this.getAccountUserIdx());
//        if (userScessionRepository.findByUserIdx(this.getAccountUserIdx()) != null) {
//            throw new RuntimeException(ErrorCodeType.USER_DUPLICATE.getValue() + " -> " + secession.getUserIdx());
//        }
//
//        try {
//            userScessionRepository.save(secession.toEntity());
//            userDetailEntity.updateStatus(UserStatusType.SECESSION);
//        } catch (Exception e) {
//            throw new RuntimeException(ErrorCodeType.ETC_ERROR.getValue());
//        }
//    }
//
//    public UserDetailEntity checkUserDetail(Long userIdx) {
//        return userDetailRepository.findById(userIdx).orElseThrow(() -> new RuntimeException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + userIdx));
//    }
//
//    public UsersEntity checkUser(Long userIdx) {
//        return userRepository.findById(userIdx).orElseThrow(() -> new RuntimeException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + userIdx));
//    }
//
//    public void checkUserByEmail(String email) {
//        UsersEntity userEntity = userRepository.findByEmailId(email);
//        if (userEntity != null) {
//            throw new DataIntegrityViolationException(ErrorCodeType.USER_DUPLICATE.getValue() + " -> " + email);
//        }
//    }
//
//    public AccountUser getAccountUser() {
//        return (AccountUser) request.getAttribute("accountUser");
//    }
//
//    public Long getAccountUserIdx() {
//        return Optional
//                .ofNullable((AccountUser) request.getAttribute("accountUser"))
//                .orElseThrow(() -> new RuntimeException(ErrorCodeType.ACCOUNTS_NOT_FOUNT.getValue()))
//                .getIdx();
//    }
}
