package co.kr.heeseong.eatthis;

import co.kr.heeseong.eatthis.model.User;
import co.kr.heeseong.eatthis.service.UserService;
import co.kr.heeseong.eatthis.util.Jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.LinkedHashMap;
import java.util.Map;

import static co.kr.heeseong.eatthis.ApiDocumentUtils.getDocumentRequest;
import static co.kr.heeseong.eatthis.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "eatthis.heeseong.site")
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static String token = "";

    @BeforeAll
    static void createToken(){
        User user = User.builder()
                        .idx(1)
                        .id("hhsung0120@naver.com")
                        .password("1234")
                        .nickName("nickName")
                        .build();
        token = Jwt.createToken(user);
    }

    @Test
    public void login() throws Exception{
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("id", "hhsung0120@naver.com");
        user.put("password", "1234");

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/users/login")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("login"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , requestFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("아이디")
                                        , fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
                                        , fieldWithPath("data.user.idx").type(JsonFieldType.NUMBER).description("고유번호")
                                        , fieldWithPath("data.user.id").type(JsonFieldType.STRING).description("아이디")
                                        , fieldWithPath("data.user.password").type(JsonFieldType.STRING).description("비밀번호")
                                        , fieldWithPath("data.user.nickName").type(JsonFieldType.STRING).description("닉네임")
                                        , fieldWithPath("data.user.gender").type(JsonFieldType.STRING).description("성별")
                                        , fieldWithPath("data.user.birthday").type(JsonFieldType.STRING).description("생일")
                                        , fieldWithPath("data.user.lunchAlarm").type(JsonFieldType.STRING).description("점심 알람")
                                        , fieldWithPath("data.user.lunchAlarmHour").type(JsonFieldType.STRING).description("점심 알람 시")
                                        , fieldWithPath("data.user.lunchAlarmMinute").type(JsonFieldType.STRING).description("점심 알람 분")
                                        , fieldWithPath("data.user.dinnerAlarm").type(JsonFieldType.STRING).description("저녁 알람")
                                        , fieldWithPath("data.user.dinnerAlarmHour").type(JsonFieldType.STRING).description("저녁 알람 시")
                                        , fieldWithPath("data.user.dinnerAlarmMinute").type(JsonFieldType.STRING).description("저녁 알람 분")
                                        , fieldWithPath("data.user.eventAlarm").type(JsonFieldType.STRING).description("이벤트 알람")
                                        , fieldWithPath("data.user.serviceAlarm").type(JsonFieldType.STRING).description("서비스 알람")
                                        , fieldWithPath("data.user.profileImagePath").type(JsonFieldType.STRING).description("이미지경로")
                                        , fieldWithPath("data.token").type(JsonFieldType.STRING).description("JWT 토근")
                                )
                        )
                )
                .andDo(print());
    }

    @Test
    public void signUp() throws Exception {
        Map<String, Object> user = new LinkedHashMap<>();
        String id = "hhsung" + (int)(Math.random()*10000) + "@naver.com";
        user.put("id", id);
        user.put("password", "1234");

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/users/signUp")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("signUp"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , requestFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("아이디")
                                        , fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
                                        , fieldWithPath("data.userIdx").type(JsonFieldType.NUMBER).description("고유번호")
                                )
                        )
                )
                .andDo(print());
    }

    @Test
    public void signUpDetail() throws Exception {
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("idx", 73);
        user.put("nickName", "nickName");
        user.put("gender", "MALE");
        user.put("birthday", "1992-01-25");
        user.put("profileImagePath", "naver.com");

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put("/users/signUpDetail")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("signUpDetail"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , requestFields(
                                        fieldWithPath("idx").type(JsonFieldType.NUMBER).description("고유번호")
                                        , fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임")
                                        , fieldWithPath("gender").type(JsonFieldType.STRING).description("성별")
                                        , fieldWithPath("birthday").type(JsonFieldType.STRING).description("생일")
                                        , fieldWithPath("profileImagePath").type(JsonFieldType.STRING).description("이미지경로")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
                                        , fieldWithPath("data.userIdx").type(JsonFieldType.NUMBER).description("고유번호")
                                )
                        )
                )
                .andDo(print());
    }

    @Test
    public void users() throws Exception {
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/users/{userIdx}",1)
                .header("token", token)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("users"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("userIdx").description("고유 번호")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
                                        , fieldWithPath("data.user.idx").type(JsonFieldType.NUMBER).description("고유 번호")
                                        , fieldWithPath("data.user.id").type(JsonFieldType.STRING).description("아이디")
                                        , fieldWithPath("data.user.password").type(JsonFieldType.STRING).description("비밀번호")
                                        , fieldWithPath("data.user.nickName").type(JsonFieldType.STRING).description("닉네임")
                                        , fieldWithPath("data.user.gender").type(JsonFieldType.STRING).description("성별")
                                        , fieldWithPath("data.user.birthday").type(JsonFieldType.STRING).description("생일")
                                        , fieldWithPath("data.user.lunchAlarm").type(JsonFieldType.STRING).description("점심 알람")
                                        , fieldWithPath("data.user.lunchAlarmHour").type(JsonFieldType.STRING).description("점심 알람 시")
                                        , fieldWithPath("data.user.lunchAlarmMinute").type(JsonFieldType.STRING).description("점심 알람 분")
                                        , fieldWithPath("data.user.dinnerAlarm").type(JsonFieldType.STRING).description("저녁 알람 여부")
                                        , fieldWithPath("data.user.dinnerAlarmHour").type(JsonFieldType.STRING).description("저녁 알람 시")
                                        , fieldWithPath("data.user.dinnerAlarmMinute").type(JsonFieldType.STRING).description("저녁 알람 분")
                                        , fieldWithPath("data.user.eventAlarm").type(JsonFieldType.STRING).description("이벤트 알람")
                                        , fieldWithPath("data.user.serviceAlarm").type(JsonFieldType.STRING).description("서비스 알람")
                                        , fieldWithPath("data.user.profileImagePath").type(JsonFieldType.STRING).description("이미지 경로")

                                )
                        )
                )
                .andDo(print());
    }

    @Test
    public void lunchAlarm() throws Exception {
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("lunchAlarm", "Y");
        user.put("lunchAlarmHour", 12);
        user.put("lunchAlarmMinute", 24);

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put("/users/lunchAlarm/{userIdx}",1)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("updateLunchAlarm"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("userIdx").description("고유 번호")
                                )
                                , requestFields(
                                        fieldWithPath("lunchAlarm").type(JsonFieldType.STRING).description("Y, N")
                                        , fieldWithPath("lunchAlarmHour").type(JsonFieldType.NUMBER).description("1~23")
                                        , fieldWithPath("lunchAlarmMinute").type(JsonFieldType.NUMBER).description("0~59")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
                                )
                        )
                )
                .andDo(print());
    }

    @Test
    public void dinnerAlarm() throws Exception {
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("dinnerAlarm", "N");
        user.put("dinnerAlarmHour", 12);
        user.put("dinnerAlarmMinute", 24);

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put("/users/dinnerAlarm/{userIdx}",1)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("updateDinnerAlarm"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("userIdx").description("고유 번호")
                                )
                                , requestFields(
                                        fieldWithPath("dinnerAlarm").type(JsonFieldType.STRING).description("Y, N")
                                        , fieldWithPath("dinnerAlarmHour").type(JsonFieldType.NUMBER).description("1~23")
                                        , fieldWithPath("dinnerAlarmMinute").type(JsonFieldType.NUMBER).description("0~59")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
                                )
                        )
                )
                .andDo(print());
    }

    @Test
    public void eventAlarm() throws Exception {
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("eventAlarm", "Y");

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put("/users/eventAlarm/{userIdx}",1)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("updateEventAlarm"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("userIdx").description("고유 번호")
                                )
                                , requestFields(
                                        fieldWithPath("eventAlarm").type(JsonFieldType.STRING).description("Y, N")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
                                )
                        )
                )
                .andDo(print());
    }

    @Test
    public void serviceAlarm() throws Exception {
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("serviceAlarm", "Y");

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put("/users/serviceAlarm/{userIdx}",1)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("updateServiceAlarm"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("userIdx").description("고유 번호")
                                )
                                , requestFields(
                                        fieldWithPath("serviceAlarm").type(JsonFieldType.STRING).description("Y, N")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
                                )
                        )
                )
                .andDo(print());
    }

    @Test
    public void secessionReasonList() throws Exception {
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/users/secession/{userIdx}",1)
        );

        FieldDescriptor[] response = new FieldDescriptor[]{
                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
                , fieldWithPath("data.userIdx").type(JsonFieldType.NUMBER).description("유저 고유 번호")
                , fieldWithPath("data.list[].idx").type(JsonFieldType.NUMBER).description("사유 고유 번호")
                , fieldWithPath("data.list[].reason").type(JsonFieldType.STRING).description("사유")
        };

        result.andExpect(status().isOk())
                .andDo(
                        document("secessionReasonList"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("userIdx").description("고유 번호")
                                )
                                , responseFields(response)
                        )
                )
                .andDo(print());
    }

    @Test
    public void updateSecession() throws Exception {
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("userIdx", 5);
        user.put("memo", "memo");

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/users/secession/{userIdx}",1)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("updateSecession"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("userIdx").description("고유 번호")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("성공 OK, 실패시 사유")
                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("빈 값")
                                )
                        )
                )
                .andDo(print());
    }


}
