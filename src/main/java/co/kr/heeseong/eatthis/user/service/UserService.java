package co.kr.heeseong.eatthis.user.service;

import co.kr.heeseong.eatthis.common.util.LogUtils;
import co.kr.heeseong.eatthis.common.util.StringUtils;
import co.kr.heeseong.eatthis.user.domain.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.user.domain.entity.UsersEntity;
import co.kr.heeseong.eatthis.user.domain.model.AccountUser;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final UserDetailRepository userDetailRepository;

    final SecessionRepository secessionRepository;
    final UserSecessionRepository userScessionRepository;

    final HttpServletRequest request;

    @Transactional
    public Long insertUser(AccountUser accountUser) throws Exception {
        log.info("accountUser : {}", accountUser);

        accountUserDataValidation(accountUser);

        Long userSeq;
        try {
            accountUser.setUserid(accountUser.getUserId());
            userSeq = userRepository.save(accountUser.toUsersEntity()).getSeq();
        } catch (DataIntegrityViolationException e) {
            LogUtils.errorLog("usersId duplicate exception", "accountUser", accountUser.toUsersEntity().toString(), e);
            throw new IllegalArgumentException("usersId duplicate");
        } catch (Exception e) {
            LogUtils.errorLog("users save exception", "accountUser", accountUser.toUsersEntity().toString(), e);
            throw new IllegalArgumentException("users save exception");
        }

        UserDetailEntity userDetailEntity = accountUser.toUserDetailEntity(userSeq);
        try {
            if (userDetailEntity.getUserSeq() > 0) {
                return userDetailRepository.save(userDetailEntity).getUserSeq();
            }
        } catch (Exception e) {
            LogUtils.errorLog("user detail save exception", "userDetailEntity", userDetailEntity, e);
            throw new IllegalArgumentException("user detail save exception");
        }

        throw new IllegalArgumentException("insertUser exception");
    }

    @Transactional
    public void updateUser(AccountUser accountUser) {
        isVaildUserAndTokenCheck(accountUser);

        UsersEntity usersEntity = userRepository.findById(accountUser.getUserSeq()).get();
        usersEntity.updateUserInfo(accountUser);
    }

    private void isVaildUserAndTokenCheck(AccountUser accountUser) {
        userRepository.findById(accountUser.getUserSeq()).ifPresentOrElse(
                selectUser -> {
                    AccountUser sessionUser = getAccountUser();

                    if (!accountUser.getUserSeq().equals(sessionUser.getUserSeq())) {
                        log.error("request != session");
                        log.error("request user : {}", accountUser);
                        log.error("session user : {}", sessionUser);
                        throw new IllegalArgumentException("not a valid request");
                    }

                    if (!sessionUser.getUserId().equals(selectUser.getUserId())) {
                        log.error("session != select");
                        log.error("session user : {}", sessionUser);
                        log.error("select user : {}", selectUser);
                        throw new IllegalArgumentException("not a valid request");
                    }
                },
                () -> {
                    log.error("non-existent user userSeq : {}", accountUser.getUserSeq());
                    throw new IllegalArgumentException("non-existent user");
                }
        );
    }

    private void accountUserDataValidation(AccountUser accountUser) throws Exception {
        StringUtils.isEmail(accountUser.getUserId());

        //this.existingUserId(accountUser.getUserId());

        if (!"y".equalsIgnoreCase(accountUser.getAgreeMap().get("terms"))) {
            LogUtils.errorLog(StringUtils.NOT_A_VALID_PARAMETER + "terms agree", "terms", accountUser.getAgreeMap().get("terms"));
            throw new IllegalArgumentException(StringUtils.NOT_A_VALID_PARAMETER + "terms agree : " + accountUser.getAgreeMap().get("terms"));
        }

        if (!"y".equalsIgnoreCase(accountUser.getAgreeMap().get("privacy"))) {
            LogUtils.errorLog(StringUtils.NOT_A_VALID_PARAMETER + "privacy agree", "privacy", accountUser.getAgreeMap().get("privacy"));
            throw new IllegalArgumentException(StringUtils.NOT_A_VALID_PARAMETER + "privacy agree : " + accountUser.getAgreeMap().get("privacy"));
        }

        if (!"y".equalsIgnoreCase(accountUser.getAgreeMap().get("location"))) {
            LogUtils.errorLog(StringUtils.NOT_A_VALID_PARAMETER + "location agree", "location", accountUser.getAgreeMap().get("location"));
            throw new IllegalArgumentException(StringUtils.NOT_A_VALID_PARAMETER + "location agree : " + accountUser.getAgreeMap().get("location"));
        }

        if (accountUser.getPassword().length() < 8) {
            LogUtils.errorLog(StringUtils.PASSWORD_SHORT);
            throw new IllegalArgumentException(StringUtils.PASSWORD_SHORT);
        }

        if (accountUser.getPassword().length() > 20) {
            LogUtils.errorLog(StringUtils.PASSWORD_LONG);
            throw new IllegalArgumentException(StringUtils.PASSWORD_LONG);
        }

        //TODO : 비밀번호 조합 검사

        if (!accountUser.getPassword().equals(accountUser.getCheckPassword())) {
            LogUtils.errorLog(StringUtils.PASSWORD_MISMATCH);
            throw new IllegalArgumentException(StringUtils.PASSWORD_MISMATCH);
        }
    }

    public AccountUser getAccountUser() {
        return (AccountUser) request.getAttribute("accountUser");
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
    public void existingUserId(String userId) {
        if (userRepository.findByUserId(userId) != null) {
            LogUtils.errorLog("existing user id", "userId", userId);
            throw new IllegalArgumentException("existing user id : " + userId);
        }
    }

//
//    public Long getAccountUserIdx() {
//        return Optional
//                .ofNullable((AccountUser) request.getAttribute("accountUser"))
//                .orElseThrow(() -> new RuntimeException(ErrorCodeType.ACCOUNTS_NOT_FOUNT.getValue()))
//                .getIdx();
//    }
}
