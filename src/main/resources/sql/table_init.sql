drop table alarms;
drop table user_secession;
drop table secession_reason;
drop table favorites_store;
drop table user_detail;
drop table questions;
drop table faqs;
drop table notices;
drop table users;
drop table categories;


-- 회원
ALTER TABLE users
DROP PRIMARY KEY; -- 회원 기본키

-- 회원 유니크 인덱스
DROP INDEX UIX_users ON users;

-- 회원 유니크 인덱스2
DROP INDEX UIX_users2 ON users;

-- 회원
DROP TABLE IF EXISTS users RESTRICT;

-- 회원
CREATE TABLE users (
                       seq                BIGINT       NOT NULL COMMENT '시퀀스', -- 시퀀스
                       user_id            VARCHAR(80)  NOT NULL COMMENT '유저 아이디', -- 유저아이디
                       password           VARCHAR(300) NULL     COMMENT '비밀번호', -- 비밀번호
                       nick_name          VARCHAR(50)  NOT NULL COMMENT '닉네임', -- 닉네임
                       gender             VARCHAR(5)   NOT NULL COMMENT 'MALE : 남자, FEMALE : 여자', -- 성별
                       birthday           DATE         NOT NULL COMMENT '생일', -- 생일
                       profile_image_path VARCHAR(200) NULL     COMMENT '이미지 경로', -- 프로필 이미지 경로
                       sign_up_type       VARCHAR(10)  NOT NULL COMMENT 'DEFAULT : 일반, FACEBOOK : 페이스북, GOOGLE : 구글, KAKAO : 카카오, NAVER : 네이버', -- 회원가입 구분
                       created_id         VARCHAR(80)  NOT NULL COMMENT '생성자', -- 생성자
                       created_datetime   DATETIME     NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                       modified_id        VARCHAR(80)  NULL     COMMENT '수정자', -- 수정자
                       modified_datetime  DATETIME     NULL     COMMENT '수정일시' -- 수정일시
)
    COMMENT '회원';

-- 회원
ALTER TABLE users
    ADD CONSTRAINT PK_users -- 회원 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_users
    ON users ( -- 회원
              user_id ASC,      -- 유저아이디
              sign_up_type ASC  -- 회원가입 구분
        );

-- 회원 유니크 인덱스2
CREATE UNIQUE INDEX UIX_users2
    ON users ( -- 회원
              nick_name ASC -- 닉네임
        );

ALTER TABLE users
    MODIFY COLUMN seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE users
    AUTO_INCREMENT = 1;


-- ----------------------------------------------------------------------------------------------------------------
-- 카테고리
ALTER TABLE categories
DROP PRIMARY KEY; -- 카테고리 기본키

-- 카테고리
DROP TABLE IF EXISTS categories RESTRICT;

-- 카테고리
CREATE TABLE categories (
                            seq               TINYINT      NOT NULL COMMENT '시퀀스', -- 시퀀스
                            category_name     VARCHAR(50)  NOT NULL COMMENT '카테고리 이름', -- 카테고리 이름
                            category_type     VARCHAR(100) NOT NULL COMMENT 'FAQ, QUESTIONS 등의 타입', -- 카테고리 구분
                            order_number      TINYINT      NOT NULL COMMENT '클라이언트 화면에서 보여지는 순서', -- 순서 번호
                            created_id        VARCHAR(50)  NOT NULL COMMENT '생성자', -- 생성자
                            created_datetime  DATETIME     NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                            modified_id       VARCHAR(50)  NULL     COMMENT '수정자', -- 수정자
                            modified_datetime DATETIME     NULL     COMMENT '수정일시' -- 수정일시
)
    COMMENT '카테고리';

-- 카테고리
ALTER TABLE categories
    ADD CONSTRAINT PK_categories -- 카테고리 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

ALTER TABLE categories
    MODIFY COLUMN seq TINYINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE categories
    AUTO_INCREMENT = 1;

-- ----------------------------------------------------------------------------------------------------------------

-- 알람 리스트
ALTER TABLE alarms
DROP FOREIGN KEY FK_users_TO_alarms; -- 회원 -> 알람 리스트

-- 알람 리스트
ALTER TABLE alarms
DROP PRIMARY KEY; -- 알람 리스트 기본키

-- 알람 리스트
DROP TABLE IF EXISTS alarms RESTRICT;

-- 알람 리스트
CREATE TABLE alarms (
                        seq               BIGINT        NOT NULL COMMENT '시퀀스', -- 시퀀스
                        user_seq          BIGINT        NOT NULL COMMENT '유저 시퀀스', -- 유저 시퀀스
                        contents          VARCHAR(1000) NOT NULL COMMENT '알림 내용', -- 내용
                        alarm_type        VARCHAR(100)  NOT NULL COMMENT '알람 구분', -- 알람 구분
                        created_id        VARCHAR(50)   NOT NULL COMMENT '생성자', -- 생성자
                        created_datetime  DATETIME      NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                        modified_id       VARCHAR(50)   NULL     COMMENT '수정자', -- 수정자
                        modified_datetime DATETIME      NULL     COMMENT '수정일시' -- 수정일시
)
    COMMENT '알람 리스트';

-- 알람 리스트
ALTER TABLE alarms
    ADD CONSTRAINT PK_alarms -- 알람 리스트 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

ALTER TABLE alarms
    MODIFY COLUMN seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE alarms
    AUTO_INCREMENT = 1;

-- 알람 리스트
ALTER TABLE alarms
    ADD CONSTRAINT FK_users_TO_alarms -- 회원 -> 알람 리스트
        FOREIGN KEY (
                     user_seq -- 유저 시퀀스
            )
            REFERENCES users ( -- 회원
                              seq -- 시퀀스
                );

-- ----------------------------------------------------------------------------------------------------------------

-- 회원 탈퇴
ALTER TABLE user_secession
DROP FOREIGN KEY FK_users_TO_user_secession; -- 회원 -> 회원 탈퇴

-- 회원 탈퇴
ALTER TABLE user_secession
DROP FOREIGN KEY FK_secession_reason_TO_user_secession; -- 탈퇴 사유 -> 회원 탈퇴

-- 회원 탈퇴
ALTER TABLE user_secession
DROP PRIMARY KEY; -- 회원 탈퇴 기본키

-- 회원 탈퇴
DROP TABLE IF EXISTS user_secession RESTRICT;

-- 회원 탈퇴
CREATE TABLE user_secession (
                                seq               BIGINT       NOT NULL COMMENT '시퀀스', -- 시퀀스
                                reason_seq        BIGINT       NOT NULL COMMENT '사유 시퀀스', -- 사유 시퀀스
                                user_seq          BIGINT       NOT NULL COMMENT '유저 시퀀스', -- 유저 시퀀스
                                memo              VARCHAR(255) NOT NULL COMMENT '상세 메모', -- 메모
                                created_id        VARCHAR(50)  NOT NULL COMMENT '생성자', -- 생성자
                                created_datetime  DATETIME     NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                                modified_id       VARCHAR(50)  NULL     COMMENT '수정자', -- 수정자
                                modified_datetime DATETIME     NULL     COMMENT '수정일시' -- 수정일시
)
    COMMENT '회원 탈퇴';

-- 회원 탈퇴
ALTER TABLE user_secession
    ADD CONSTRAINT PK_user_secession -- 회원 탈퇴 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

ALTER TABLE user_secession
    MODIFY COLUMN seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE user_secession
    AUTO_INCREMENT = 1;

-- 회원 탈퇴
ALTER TABLE user_secession
    ADD CONSTRAINT FK_users_TO_user_secession -- 회원 -> 회원 탈퇴
        FOREIGN KEY (
                     user_seq -- 유저 시퀀스
            )
            REFERENCES users ( -- 회원
                              seq -- 시퀀스
                );

-- 회원 탈퇴
ALTER TABLE user_secession
    ADD CONSTRAINT FK_secession_reason_TO_user_secession -- 탈퇴 사유 -> 회원 탈퇴
        FOREIGN KEY (
                     reason_seq -- 사유 시퀀스
            )
            REFERENCES secession_reason ( -- 탈퇴 사유
                                         seq -- 시퀀스
                );

-- ----------------------------------------------------------------------------------------------------------------

-- 즐겨찾는 매장
ALTER TABLE favorites_store
DROP FOREIGN KEY FK_users_TO_favorites_store; -- 회원 -> 즐겨찾는 매장

-- 즐겨찾는 매장
ALTER TABLE favorites_store
DROP PRIMARY KEY; -- 즐겨찾는 매장 기본키

-- 즐겨찾는 매장
DROP TABLE IF EXISTS favorites_store RESTRICT;

-- 즐겨찾는 매장
CREATE TABLE favorites_store (
                                 seq               BIGINT      NOT NULL COMMENT '시퀀스', -- 시퀀스
                                 store_seq         BIGINT      NOT NULL COMMENT '매장 시퀀스', -- 매장 시퀀스
                                 created_id        VARCHAR(50) NOT NULL COMMENT '생성자', -- 생성자
                                 created_datetime  DATETIME    NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                                 modified_id       VARCHAR(50) NULL     COMMENT '수정자', -- 수정자
                                 modified_datetime DATETIME    NULL     COMMENT '수정일시', -- 수정일시
                                 user_seq          BIGINT      NOT NULL COMMENT '유저 시퀀스' -- 유저 시퀀스
)
    COMMENT '즐겨찾는 매장';

-- 즐겨찾는 매장
ALTER TABLE favorites_store
    ADD CONSTRAINT PK_favorites_store -- 즐겨찾는 매장 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

ALTER TABLE favorites_store
    MODIFY COLUMN seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE favorites_store
    AUTO_INCREMENT = 1;

-- 즐겨찾는 매장
ALTER TABLE favorites_store
    ADD CONSTRAINT FK_users_TO_favorites_store -- 회원 -> 즐겨찾는 매장
        FOREIGN KEY (
                     user_seq -- 유저 시퀀스
            )
            REFERENCES users ( -- 회원
                              seq -- 시퀀스
                );

-- ----------------------------------------------------------------------------------------------------------------

-- 회원 상세
ALTER TABLE user_detail
DROP FOREIGN KEY FK_users_TO_user_detail; -- 회원 -> 회원 상세

-- 회원 상세
ALTER TABLE user_detail
DROP PRIMARY KEY; -- 회원 상세 기본키

-- 회원 상세
DROP TABLE IF EXISTS user_detail RESTRICT;

-- 회원 상세
CREATE TABLE user_detail (
                             seq                  BIGINT      NOT NULL COMMENT '시퀀스', -- 시퀀스
                             user_seq             BIGINT      NOT NULL COMMENT '유저 시퀀스', -- 유저 시퀀스
                             lunch_alarm_use_yn   VARCHAR(1)  NOT NULL COMMENT '점심 알람 여부', -- 점심 알람 사용여부
                             lunch_alarm_time     TIME        NOT NULL COMMENT '점심 알람 시간', -- 점심 알람 시간
                             dinner_alarm_use_yn  VARCHAR(1)  NOT NULL COMMENT '저녁 알람 여부', -- 저녁 알람 사용여부
                             dinner_alarm_time    TIME        NOT NULL COMMENT '저녁 알람 시간', -- 저녁 알람 시간
                             event_alarm_use_yn   VARCHAR(1)  NOT NULL COMMENT '이벤트 알람 여부', -- 이벤트 알람 사용여부
                             service_alarm_use_yn VARCHAR(1)  NOT NULL COMMENT '서비스 알람 여부', -- 서비스 알람 사용여부
                             user_status_type     VARCHAR(10) NOT NULL COMMENT 'NORMAL : 정상, STOP : 중지, DORMANCY : 휴면, SECESSION : 탈퇴', -- 유저 상태
                             privacy_agree        VARCHAR(1)  NOT NULL COMMENT '개인정보 동의', -- 개인정보 동의
                             terms_agree          VARCHAR(1)  NOT NULL COMMENT '약관 동의', -- 약관 동의
                             location_agree       VARCHAR(1)  NOT NULL COMMENT '위치 동의', -- 위치 동의
                             created_id           VARCHAR(50) NOT NULL COMMENT '생성자', -- 생성자
                             created_datetime     DATETIME    NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                             modified_id          VARCHAR(50) NULL     COMMENT '수정자', -- 수정자
                             modified_datetime    DATETIME    NULL     COMMENT '수정일시' -- 수정일시
)
    COMMENT '회원 상세';

-- 회원 상세
ALTER TABLE user_detail
    ADD CONSTRAINT PK_user_detail -- 회원 상세 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

ALTER TABLE user_detail
    MODIFY COLUMN seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE user_detail
    AUTO_INCREMENT = 1;

-- 회원 상세
ALTER TABLE user_detail
    ADD CONSTRAINT FK_users_TO_user_detail -- 회원 -> 회원 상세
        FOREIGN KEY (
                     user_seq -- 유저 시퀀스
            )
            REFERENCES users ( -- 회원
                              seq -- 시퀀스
                );

-- ----------------------------------------------------------------------------------------------------------------

-- 묻고 답하기
ALTER TABLE questions
DROP FOREIGN KEY FK_categories_TO_questions; -- 카테고리 -> 묻고 답하기

-- 묻고 답하기
ALTER TABLE questions
DROP FOREIGN KEY FK_users_TO_questions; -- 회원 -> 묻고 답하기

-- 묻고 답하기
ALTER TABLE questions
DROP PRIMARY KEY; -- 묻고 답하기 기본키

-- 묻고 답하기
DROP TABLE IF EXISTS questions RESTRICT;

-- 묻고 답하기
CREATE TABLE questions (
                           seq               BIGINT        NOT NULL COMMENT '시퀀스', -- 시퀀스
                           category_seq      TINYINT       NOT NULL COMMENT '카테고리 시퀀스', -- 카테고리 시퀀스
                           user_seq          BIGINT        NOT NULL COMMENT '유저 시퀀스', -- 유저 시퀀스
                           questions         VARCHAR(1000) NOT NULL COMMENT '질문', -- 질문
                           answer            VARCHAR(1000) NULL     COMMENT '답변', -- 답변
                           answer_status     VARCHAR(10)   NOT NULL COMMENT 'WAITING : 대기, COMPLETE : 완료', -- 답변 상태
                           created_id        VARCHAR(50)   NOT NULL COMMENT '생성자', -- 생성자
                           created_datetime  DATETIME      NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                           modified_id       VARCHAR(50)   NULL     COMMENT '수정자', -- 수정자
                           modified_datetime DATETIME      NULL     COMMENT '수정일시' -- 수정일시
)
    COMMENT '묻고 답하기';

-- 묻고 답하기
ALTER TABLE questions
    ADD CONSTRAINT PK_questions -- 묻고 답하기 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

ALTER TABLE questions
    MODIFY COLUMN seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE questions
    AUTO_INCREMENT = 1;

-- 묻고 답하기
ALTER TABLE questions
    ADD CONSTRAINT FK_categories_TO_questions -- 카테고리 -> 묻고 답하기
        FOREIGN KEY (
                     category_seq -- 카테고리 시퀀스
            )
            REFERENCES categories ( -- 카테고리
                                   seq -- 시퀀스
                );

-- 묻고 답하기
ALTER TABLE questions
    ADD CONSTRAINT FK_users_TO_questions -- 회원 -> 묻고 답하기
        FOREIGN KEY (
                     user_seq -- 유저 시퀀스
            )
            REFERENCES users ( -- 회원
                              seq -- 시퀀스
                );


-- ----------------------------------------------------------------------------------------------------------------
-- 자주하는 질문
ALTER TABLE faqs
DROP FOREIGN KEY FK_categories_TO_faqs; -- 카테고리 -> 자주하는 질문

-- 자주하는 질문
ALTER TABLE faqs
DROP PRIMARY KEY; -- 자주하는 질문 기본키

-- 자주하는 질문
DROP TABLE IF EXISTS faqs RESTRICT;

-- 자주하는 질문
CREATE TABLE faqs (
                      seq               BIGINT        NOT NULL COMMENT '시퀀스', -- 시퀀스
                      category_seq      TINYINT       NOT NULL COMMENT '카테고리 시퀀스', -- 카테고리 시퀀스
                      title             VARCHAR(80)   NOT NULL COMMENT '제목', -- 제목
                      contents          VARCHAR(1000) NOT NULL COMMENT '내용', -- 내용
                      created_id        VARCHAR(50)   NOT NULL COMMENT '생성자', -- 생성자
                      created_datetime  DATETIME      NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                      modified_id       VARCHAR(50)   NULL     COMMENT '수정자', -- 수정자
                      modified_datetime DATETIME      NULL     COMMENT '수정일시' -- 수정일시
)
    COMMENT '자주하는 질문';

-- 자주하는 질문
ALTER TABLE faqs
    ADD CONSTRAINT PK_faqs -- 자주하는 질문 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

ALTER TABLE faqs
    MODIFY COLUMN seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE faqs
    AUTO_INCREMENT = 1;

-- 자주하는 질문
ALTER TABLE faqs
    ADD CONSTRAINT FK_categories_TO_faqs -- 카테고리 -> 자주하는 질문
        FOREIGN KEY (
                     category_seq -- 카테고리 시퀀스
            )
            REFERENCES categories ( -- 카테고리
                                   seq -- 시퀀스
                );

-- ----------------------------------------------------------------------------------------------------------------

-- 공지사항
ALTER TABLE notices
DROP PRIMARY KEY; -- 공지사항 기본키

-- 공지사항
DROP TABLE IF EXISTS notices RESTRICT;

-- 공지사항
CREATE TABLE notices (
                         seq               BIGINT        NOT NULL COMMENT '시퀀스', -- 시퀀스
                         title             VARCHAR(80)   NOT NULL COMMENT '제목', -- 제목
                         contents          VARCHAR(1000) NOT NULL COMMENT '내용', -- 내용
                         created_id        VARCHAR(50)   NOT NULL COMMENT '생성자', -- 생성자
                         created_datetime  DATETIME      NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
                         modified_id       VARCHAR(50)   NULL     COMMENT '수정자', -- 수정자
                         modified_datetime DATETIME      NULL     COMMENT '수정일시' -- 수정일시
)
    COMMENT '공지사항';

-- 공지사항
ALTER TABLE notices
    ADD CONSTRAINT PK_notices -- 공지사항 기본키
        PRIMARY KEY (
                     seq -- 시퀀스
            );

ALTER TABLE notices
    MODIFY COLUMN seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스';

ALTER TABLE notices
    AUTO_INCREMENT = 1;