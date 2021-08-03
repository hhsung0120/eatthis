package co.kr.heeseong.eatthis.Enum;

public enum ErrorCodeType {

      USER_NOT_FOUND("존재하지 않는 회원 입니다.")
    , STORE_NOT_FOUND("존재하지 않는 매장 입니다.")
    , MENU_NOT_FOUND("존재하지 않는 메뉴 입니다.")
    , USER_DUPLICATE("중복 된 회원 입니다.")
    , INVALID_ARGUMENT("올바른 인자가 아닙니다.")
    , POST_NOT_FOUND("존재하지 않는 게시물 입니다.")
    , INVALID_PASSWORD("패스워드를 확인해 주세요.")
    , INVALID_TOKEN("유효한 토큰이 아닙니다.")
    , ETC_ERROR("기타 오류 입니다.")
    , ACCOUNTS_NOT_FOUNT("ACCOUNTS 정보를 찾을 수 없습니다.")
    , FILE_UPLOAD_ERROR("파일 업로드 중 오류가 발생 하였습니다.")

    ;

    String value = "";
    ErrorCodeType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
