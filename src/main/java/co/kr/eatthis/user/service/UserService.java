package co.kr.eatthis.user.service;

import co.kr.eatthis.common.Enum.ErrorCode;
import co.kr.eatthis.common.Enum.UserStatusType;
import co.kr.eatthis.common.util.LogUtils;
import co.kr.eatthis.common.util.StringUtils;
import co.kr.eatthis.user.domain.entity.SecessionReasonEntity;
import co.kr.eatthis.user.domain.entity.UserDetailEntity;
import co.kr.eatthis.user.domain.entity.UsersEntity;
import co.kr.eatthis.user.domain.model.AccountUser;
import co.kr.eatthis.user.domain.model.SecessionReason;
import co.kr.eatthis.user.domain.model.UserSecession;
import co.kr.eatthis.user.domain.repository.SecessionReasonRepository;
import co.kr.eatthis.user.domain.repository.UserDetailRepository;
import co.kr.eatthis.user.domain.repository.UserRepository;
import co.kr.eatthis.user.domain.repository.UserSecessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final UserDetailRepository userDetailRepository;

    final SecessionReasonRepository secessionReasonRepository;
    final UserSecessionRepository userScessionRepository;

    final HttpServletRequest request;

    @Transactional
    public Long insertUser(AccountUser accountUser) throws Exception {
        accountUserDataValidation(accountUser);

        Long userSeq;
        try {
            userSeq = userRepository.save(accountUser.toUsersEntity()).getSeq();
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

                    if (!selectUser.getUserDetailEntity().getUserStatusType().equals(UserStatusType.SIGNING)) {
                        log.error("you are already a registered user");
                        throw new IllegalArgumentException("already a registered user");
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

        //TODO 가입 타입도 검사
        //this.existingUserId(accountUser.getUserId());

        if (!"y".equalsIgnoreCase(accountUser.getAgreeMap().get("terms"))) {
            LogUtils.errorLog(ErrorCode.NOT_A_VALID_PARAMETER + " terms agree", "terms", accountUser.getAgreeMap().get("terms"));
            throw new IllegalArgumentException(ErrorCode.NOT_A_VALID_PARAMETER + " terms agree : " + accountUser.getAgreeMap().get("terms"));
        }

        if (!"y".equalsIgnoreCase(accountUser.getAgreeMap().get("privacy"))) {
            LogUtils.errorLog(ErrorCode.NOT_A_VALID_PARAMETER + " privacy agree", "privacy", accountUser.getAgreeMap().get("privacy"));
            throw new IllegalArgumentException(ErrorCode.NOT_A_VALID_PARAMETER + " privacy agree : " + accountUser.getAgreeMap().get("privacy"));
        }

        if (!"y".equalsIgnoreCase(accountUser.getAgreeMap().get("location"))) {
            LogUtils.errorLog(ErrorCode.NOT_A_VALID_PARAMETER + " location agree", "location", accountUser.getAgreeMap().get("location"));
            throw new IllegalArgumentException(ErrorCode.NOT_A_VALID_PARAMETER + " location agree : " + accountUser.getAgreeMap().get("location"));
        }
    }

    public AccountUser getAccountUser() {

        AccountUser accountUser = null;
        try {
            accountUser = (AccountUser) request.getAttribute("accountUser");
            if (accountUser == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            LogUtils.errorLog("getAccountUser() Null Pointer Exception", e);
            throw new NullPointerException("Login user does not exist.");
        }

        return accountUser;
    }

    public boolean checkNickName(String nickName) {
        //String nickName = accountUser.getNickName();

        //TODO : 나중에 닉네임 벨리데이션 만들어야함
        if (nickName == null || "".equals(nickName) || nickName.length() > 10) {
            log.info("invalid nick name : {}", nickName);
            throw new IllegalArgumentException("invalid nick name : [" + nickName + "]");
        }

        return userRepository.findByNickName(nickName) == null;
    }

    public AccountUser loginProcess(AccountUser accountUser) {
        try {
            StringUtils.isEmail(accountUser.getUserId());
        } catch (Exception e) {
            log.error(ErrorCode.NOT_A_VALID_EMAIL_FORMAT.getMessageKr() + " {}", accountUser.getUserId());
            throw new IllegalArgumentException(ErrorCode.NOT_A_VALID_EMAIL_FORMAT.getMessageKr());
        }

        UsersEntity userEntity =
                Optional.ofNullable(userRepository.findByUserIdAndSignUpType(accountUser.getUserId(), accountUser.getSignUpType()))
                        .orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.getMessageKr()));

        return new AccountUser(userEntity);
    }

    public void checkLoginUser(Long seq) {
        if (getAccountUser().getUserSeq() != seq) {
            LogUtils.errorLog("checkLoginUser - parameter seq != session req", "session seq", getAccountUser().getUserSeq()
                    , "parameter seq", seq);
            throw new IllegalArgumentException(ErrorCode.NOT_A_VALID_REQUEST.getMessageKr());
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

    public List<SecessionReason> getSecessionList() {

        List<SecessionReasonEntity> list;
        try {
            list = secessionReasonRepository.findAllForOrderNumberAsc();
            return SecessionReason.entityToList(list);
        } catch (Exception e) {
            LogUtils.errorLog("secession form request exception");
            throw new IllegalArgumentException("secession form request exception");
        }
    }

    public boolean insertUserSecession(UserSecession userSecession) {

        AccountUser accountUser = getAccountUser();
        userSecession.setUserSeq(accountUser.getUserSeq());

        try {
            userScessionRepository.save(userSecession.toEntity());
            return true;
        } catch (Exception e){
            LogUtils.errorLog("insertUserSecession exception", "data", userSecession, e);
            throw new IllegalArgumentException("insertUserSecession exception");
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
