package co.kr.eatthis.common.Enum;

public enum ErrorCode {

    //계정
    USER_NOT_FOUND("존재하지 않는 회원 입니다", "non-existent user")
    , USER_DUPLICATE("중복 된 회원 입니다", "usersId duplicate exception")
    , INVALID_PASSWORD("패스워드를 확인해 주세요", "")
    , INVALID_TOKEN("유효한 토큰이 아닙니다", "invalid token")

    , STORE_NOT_FOUND("존재하지 않는 매장 입니다", "")
    , MENU_NOT_FOUND("존재하지 않는 메뉴 입니다", "")
    , INVALID_ARGUMENT("올바른 인자가 아닙니다", "")
    , POST_NOT_FOUND("존재하지 않는 게시물 입니다", "")

    //공통
    , DECRYPT_EXCEPTION("복호화 오류", "decrypt exception")
    , ACCOUNTS_NOT_FOUNT("ACCOUNTS 정보를 찾을 수 없습니다", "")
    , FILE_UPLOAD_ERROR("파일 업로드 중 오류가 발생 하였습니다", "file uploading failed")
    , NOT_A_VALID_REQUEST("유효한 요청이 아닙니다", "not a valid request")
    , NOT_A_VALID_EMAIL_FORMAT("유효한 이메일 형식이 아닙니다", "not a valid email format")
    , ETC_ERROR("기타 오류 입니다", "other errors")
    , SERVER_ERROR("서버 오류 입니다", "server errors")
    , MUST_NOT_BE_NULL("NULL이 아니어야 합니다" ,"must not be null")
    , NOT_A_VALID_PARAMETER("유효한 파라메가 아닙니다", "not a valid parameter")
    , NOT_A_VALID("유효하지 않음", "not a valid")
    , PASSWORD_MISMATCH("패스워드가 일치하지 않습니다", "password mismatch")
    , PASSWORD_SHORT("패스워드는 최소 8자리 이여야 합니다", "password less than 8 digits")
    , PASSWORD_LONG("패스워드는 최소 20자리 입니다.", "password more than 20 digits")

    ;

    String messageKr = "";
    String messageEn = "";

    ErrorCode(String messageKr, String messageEn) {
        this.messageKr = messageKr;
        this.messageEn = messageEn;
    }

    public String getMessageEn() {
        return this.messageEn;
    }
}
