package co.kr.heeseong.eatthis;

import co.kr.heeseong.eatthis.common.util.Jwt;
import co.kr.heeseong.eatthis.common.util.SecretAes;
import co.kr.heeseong.eatthis.common.util.SecretSha;
import co.kr.heeseong.eatthis.user.domain.model.AccountUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class AccountUserControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    private static String token = "";

    @BeforeAll
    static void createToken() {
        AccountUser accountUser = AccountUser.builder()
                .idx(1)
                .id("hhsung0120@naver.com")
                .password("1234")
                .nickName("nickName")
                .build();
        token = Jwt.createToken(accountUser);
    }

//    @Test
//    public void login() throws Exception {
//        Map<String, Object> user = new LinkedHashMap<>();
//        user.put("id", "hhsung0120@naver.com");
//        user.put("password", "1234");
//
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .post("/users/login")
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("login"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , requestFields(
//                                        fieldWithPath("id").type(JsonFieldType.STRING).description("아이디")
//                                        , fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
//                                )
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
//                                        , fieldWithPath("data.user.idx").type(JsonFieldType.NUMBER).description("고유번호")
//                                        , fieldWithPath("data.user.id").type(JsonFieldType.STRING).description("아이디")
//                                        , fieldWithPath("data.user.password").type(JsonFieldType.STRING).description("비밀번호")
//                                        , fieldWithPath("data.user.nickName").type(JsonFieldType.STRING).description("닉네임")
//                                        , fieldWithPath("data.user.gender").type(JsonFieldType.STRING).description("성별")
//                                        , fieldWithPath("data.user.birthday").type(JsonFieldType.STRING).description("생일")
//                                        , fieldWithPath("data.user.lunchAlarm").type(JsonFieldType.STRING).description("점심 알람")
//                                        , fieldWithPath("data.user.lunchAlarmHour").type(JsonFieldType.STRING).description("점심 알람 시")
//                                        , fieldWithPath("data.user.lunchAlarmMinute").type(JsonFieldType.STRING).description("점심 알람 분")
//                                        , fieldWithPath("data.user.dinnerAlarm").type(JsonFieldType.STRING).description("저녁 알람")
//                                        , fieldWithPath("data.user.dinnerAlarmHour").type(JsonFieldType.STRING).description("저녁 알람 시")
//                                        , fieldWithPath("data.user.dinnerAlarmMinute").type(JsonFieldType.STRING).description("저녁 알람 분")
//                                        , fieldWithPath("data.user.eventAlarm").type(JsonFieldType.STRING).description("이벤트 알람")
//                                        , fieldWithPath("data.user.serviceAlarm").type(JsonFieldType.STRING).description("서비스 알람")
//                                        , fieldWithPath("data.user.profileImagePath").type(JsonFieldType.STRING).description("이미지경로")
//                                        , fieldWithPath("data.token").type(JsonFieldType.STRING).description("JWT 토근")
//                                )
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void signUp() throws Exception {
//        Map<String, Object> user = new LinkedHashMap<>();
//        String id = "hhsung" + (int) (Math.random() * 10000) + "@naver.com";
//        user.put("id", id);
//        user.put("password", "1234");
//
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .post("/users/signUp")
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("signUp"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , requestFields(
//                                        fieldWithPath("id").type(JsonFieldType.STRING).description("아이디")
//                                        , fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
//                                )
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
//                                        , fieldWithPath("data.userIdx").type(JsonFieldType.NUMBER).description("고유번호")
//                                        , fieldWithPath("data.token").type(JsonFieldType.STRING).description("JWT TOKEN")
//                                )
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void signUpDetail() throws Exception {
//        Map<String, Object> user = new LinkedHashMap<>();
//        user.put("idx", 1);
//        user.put("nickName", "nickName");
//        user.put("gender", "MALE");
//        user.put("birthday", "1992-01-25");
//        user.put("profileImagePath", "naver.com");
//
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .put("/users/signUpDetail")
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("signUpDetail"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , requestFields(
//                                        fieldWithPath("idx").type(JsonFieldType.NUMBER).description("고유번호")
//                                        , fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임")
//                                        , fieldWithPath("gender").type(JsonFieldType.STRING).description("성별")
//                                        , fieldWithPath("birthday").type(JsonFieldType.STRING).description("생일")
//                                        , fieldWithPath("profileImagePath").type(JsonFieldType.STRING).description("이미지경로")
//                                )
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
//                                        , fieldWithPath("data.userIdx").type(JsonFieldType.NUMBER).description("고유번호")
//                                )
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void users() throws Exception {
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .get("/users")
//                        .header("token", token)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("users"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
//                                        , fieldWithPath("data.user.idx").type(JsonFieldType.NUMBER).description("고유 번호")
//                                        , fieldWithPath("data.user.id").type(JsonFieldType.STRING).description("아이디")
//                                        , fieldWithPath("data.user.password").type(JsonFieldType.STRING).description("비밀번호")
//                                        , fieldWithPath("data.user.nickName").type(JsonFieldType.STRING).description("닉네임")
//                                        , fieldWithPath("data.user.gender").type(JsonFieldType.STRING).description("성별")
//                                        , fieldWithPath("data.user.birthday").type(JsonFieldType.STRING).description("생일")
//                                        , fieldWithPath("data.user.lunchAlarm").type(JsonFieldType.STRING).description("점심 알람")
//                                        , fieldWithPath("data.user.lunchAlarmHour").type(JsonFieldType.STRING).description("점심 알람 시")
//                                        , fieldWithPath("data.user.lunchAlarmMinute").type(JsonFieldType.STRING).description("점심 알람 분")
//                                        , fieldWithPath("data.user.dinnerAlarm").type(JsonFieldType.STRING).description("저녁 알람 여부")
//                                        , fieldWithPath("data.user.dinnerAlarmHour").type(JsonFieldType.STRING).description("저녁 알람 시")
//                                        , fieldWithPath("data.user.dinnerAlarmMinute").type(JsonFieldType.STRING).description("저녁 알람 분")
//                                        , fieldWithPath("data.user.eventAlarm").type(JsonFieldType.STRING).description("이벤트 알람")
//                                        , fieldWithPath("data.user.serviceAlarm").type(JsonFieldType.STRING).description("서비스 알람")
//                                        , fieldWithPath("data.user.profileImagePath").type(JsonFieldType.STRING).description("이미지 경로")
//
//                                )
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void lunchAlarm() throws Exception {
//        Map<String, Object> user = new LinkedHashMap<>();
//        user.put("lunchAlarm", "Y");
//        user.put("lunchAlarmHour", 12);
//        user.put("lunchAlarmMinute", 24);
//
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .put("/users/lunchAlarm")
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("token", token)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("updateLunchAlarm"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , requestFields(
//                                        fieldWithPath("lunchAlarm").type(JsonFieldType.STRING).description("Y, N")
//                                        , fieldWithPath("lunchAlarmHour").type(JsonFieldType.NUMBER).description("1~23")
//                                        , fieldWithPath("lunchAlarmMinute").type(JsonFieldType.NUMBER).description("0~59")
//                                )
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
//                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
//                                )
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void dinnerAlarm() throws Exception {
//        Map<String, Object> user = new LinkedHashMap<>();
//        user.put("dinnerAlarm", "N");
//        user.put("dinnerAlarmHour", 12);
//        user.put("dinnerAlarmMinute", 24);
//
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .put("/users/dinnerAlarm", 1)
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("token", token)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("updateDinnerAlarm"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , requestFields(
//                                        fieldWithPath("dinnerAlarm").type(JsonFieldType.STRING).description("Y, N")
//                                        , fieldWithPath("dinnerAlarmHour").type(JsonFieldType.NUMBER).description("1~23")
//                                        , fieldWithPath("dinnerAlarmMinute").type(JsonFieldType.NUMBER).description("0~59")
//                                )
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
//                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
//                                )
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void eventAlarm() throws Exception {
//        Map<String, Object> user = new LinkedHashMap<>();
//        user.put("eventAlarm", "Y");
//
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .put("/users/eventAlarm", 1)
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("token", token)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("updateEventAlarm"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , requestFields(
//                                        fieldWithPath("eventAlarm").type(JsonFieldType.STRING).description("Y, N")
//                                )
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
//                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
//                                )
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void serviceAlarm() throws Exception {
//        Map<String, Object> user = new LinkedHashMap<>();
//        user.put("serviceAlarm", "Y");
//
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .put("/users/serviceAlarm", 1)
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("token", token)
//
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("updateServiceAlarm"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , requestFields(
//                                        fieldWithPath("serviceAlarm").type(JsonFieldType.STRING).description("Y, N")
//                                )
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
//                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
//                                )
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void secessionReasonList() throws Exception {
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .get("/users/secession")
//                        .header("token", token)
//        );
//
//        FieldDescriptor[] response = new FieldDescriptor[]{
//                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
//                , fieldWithPath("data.userIdx").type(JsonFieldType.NUMBER).description("유저 고유 번호")
//                , fieldWithPath("data.list[].idx").type(JsonFieldType.NUMBER).description("사유 고유 번호")
//                , fieldWithPath("data.list[].reason").type(JsonFieldType.STRING).description("사유")
//        };
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("secessionReasonList"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , responseFields(response)
//                        )
//                )
//                .andDo(print());
//    }
//
//    @Test
//    public void updateSecession() throws Exception {
//        Map<String, Object> user = new LinkedHashMap<>();
//        user.put("userIdx", 5);
//        user.put("memo", "memo");
//
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .post("/users/secession")
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("token", token)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("updateSecession"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , responseFields(
//                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
//                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
//                                )
//                        )
//                )
//                .andDo(print());
//    }

    @Test
    public void signUp() throws Exception {

        String email = "hhsung0120@naver.com";
        String password = SecretSha.encryptPassword("1234");
        String checkPassword = SecretSha.encryptPassword("1235");
        String terms = "Y";
        String privacy = "Y";
        String location = "Y";

        Map<String, Object> data = new HashMap<>();
        data.put("email", email);
        data.put("password", password);
        data.put("checkPassword", checkPassword);
        data.put("terms", terms);
        data.put("location", location);
        System.out.println("Map : " + data);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(data);
        System.out.println("json obj : " + json);

        AccountUser accountUser = AccountUser.signUpOneStepInfoBuilder()
                .userId(email)
                .password(password)
                .checkPassword(checkPassword)
                .termsAgree(terms)
                .privacyAgree(privacy)
                .locationAgree(location)
                .build();

        System.out.println(accountUser.toString());
        System.out.println(SecretAes.encrypt(json));
        System.out.println("비밀번호 글자 수  : " + "6Wn7FguCRRrCCThipwsgqtFk4KNH+AGCfTr3B+5AoauJBec51+xrs7tiewKVditP05w/GyXxoRpiNKGl0iDsw3QXiaaO3c9y".length());
        System.out.println("데이터 암호문 : " + "6pW3gVl70VetMWTNfK2Nk5JScqAhYnWwR7h1WfBI30R3e1r_i1aM0f2TlNG6l8413uSWninH_TnoZn4hpjqo865VimCM6npoZXMVS9NYFhymH4tlKWsnQ0BZ64bVgiCAU8gkjcOsK09_o2HyBppHrt9Qu75qPLJELL38J8eLcXRhL6qewt6TkfHn4w84Y2f-hUww7k6K_ZgebQ-51jtldfDa-Pc3SEyYSFgRzuSKUncxmmim0CVVysIuKw6DAr1ycmsvwI6kg5zJgCqiqf3sZJ_2TXBGjWj-rkZzayZnDybfANW4gVO3dCRxChpL67rNOT0qVyjzLOx78roE7hd_hV4nA3w5ygN-MXwSQ7-L5hQYDG4KOKZkfEx04YRYxuO1Ms6OBZ-FDO4Pl_odwyJybfq9qd3HvkNaqAkZ3C50NwD2Bs_vcwrHrFDPaSyEEMvNI_UJr7mROrAyxP1w-UpXdQrg2fjp_9GTWyzP3SUhS5Zua00b-3JaZ-KEqjQ0eOIguu2h9hp7nKVKzHBJWt1KeLrzEYB3ebFxTMHUYRG1hEKBBFsizWeBik1o_yhq5IOKS-7F84pQf6MCz8v4NOlwUTvLEJ_mW6EG9-X2jEoAg1oENM3PN-nsESSnzbqcy-cmbUFomLwpMllZtNFtYo0zJeQIa25KESVLBi0JGG284FYU-w-Bd6pp2zH0Rg_d6PpNDhe2g2hWz9B-lgnbhwsW8Mg1eiFnNDzPVb1iUS85qEQ".length());

    }

    @Test
    public void test() throws Exception{
//        List<Map<String, Object>> listInMap = new ArrayList<>();
//        Map<String, Object> data1 = new HashMap<>();
//        data1.put("123", "123");
//        data1.put("1234", "123");
//        data1.put("1235", "123");
//
//        Map<String, Object> data2 = new HashMap<>();
//        data2.put("123", "123");
//        data2.put("1234", "123");
//        data2.put("1235", "123");
//
//        listInMap.add(data1);
//        listInMap.add(data2);
//
//        List<Board> listInBoard = new ArrayList<>();
//        Board board = new Board(1L, "board");
//        Board board1 = new Board(1L, "board");
//        Board board2 = new Board(1L, "board");
//        listInBoard.add(board);
//        listInBoard.add(board1);
//        listInBoard.add(board2);
//
//        Map<String, List<Object>> data = new HashMap<>();
//        List<Object> test = new ArrayList<>();
//        test.add("1111");
//        test.add("11112");
//        test.add("11113");
//
//        List<Object> test2 = new ArrayList<>();
//        test2.add("1111");
//        test2.add("11112");
//        test2.add("11113");
//
//        data.put("key1", test);
//        data.put("key2", test2);
//
//        Map<String, List<Board>> mpaInList = new HashMap<>();
//        mpaInList.put("key1", listInBoard);
//        mpaInList.put("key2", listInBoard);
//
//
//        System.out.println("리스트 안에 맵 =>" + listInMap);
//        System.out.println("리스트 안에 보드 객체 =>" + listInBoard);
//        System.out.println("맵 안에 List<Object> =>" + data);
//        System.out.println("맵 안에 List<Board> =>" + mpaInList);

    }

}
