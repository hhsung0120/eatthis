package co.kr.heeseong.eatthis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.LinkedHashMap;
import java.util.Map;

import static co.kr.heeseong.eatthis.ApiDocumentUtils.getDocumentRequest;
import static co.kr.heeseong.eatthis.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
                                        , fieldWithPath("data.user.idx").type(JsonFieldType.NUMBER).description("고유 번호")
                                        , fieldWithPath("data.user.id").type(JsonFieldType.STRING).description("아이디")
                                        , fieldWithPath("data.user.password").type(JsonFieldType.STRING).description("비밀번호")
                                        , fieldWithPath("data.user.nickName").type(JsonFieldType.STRING).description("닉네임")
                                        , fieldWithPath("data.user.gender").type(JsonFieldType.STRING).description("성별")
                                        , fieldWithPath("data.user.birthday").type(JsonFieldType.STRING).description("생일")
                                        , fieldWithPath("data.user.lunchAlarm").type(JsonFieldType.STRING).description("점심 알람 여부")
                                        , fieldWithPath("data.user.lunchAlarmHour").type(JsonFieldType.STRING).description("점심 알람 시")
                                        , fieldWithPath("data.user.lunchAlarmMinute").type(JsonFieldType.STRING).description("점심 알람 분")
                                        , fieldWithPath("data.user.dinnerAlarm").type(JsonFieldType.STRING).description("저녁 알람 여부")
                                        , fieldWithPath("data.user.dinnerAlarmHour").type(JsonFieldType.STRING).description("저녁 알람 시")
                                        , fieldWithPath("data.user.dinnerAlarmMinute").type(JsonFieldType.STRING).description("저녁 알람 분")
                                        , fieldWithPath("data.user.eventAlarm").type(JsonFieldType.STRING).description("이벤트 알람 여부")
                                        , fieldWithPath("data.user.serviceAlarm").type(JsonFieldType.STRING).description("서비스 알람 여부")
                                        , fieldWithPath("data.user.profileImagePath").type(JsonFieldType.STRING).description("프로필 이미지 경로")
                                )
                        )
                )
                .andDo(print());
    }


    @Test
    public void users() throws Exception {
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
                                        , fieldWithPath("data.user.lunchAlarm").type(JsonFieldType.STRING).description("점심 알람 여부")
                                        , fieldWithPath("data.user.lunchAlarmHour").type(JsonFieldType.STRING).description("점심 알람 시")
                                        , fieldWithPath("data.user.lunchAlarmMinute").type(JsonFieldType.STRING).description("점심 알람 분")
                                        , fieldWithPath("data.user.dinnerAlarm").type(JsonFieldType.STRING).description("저녁 알람 여부")
                                        , fieldWithPath("data.user.dinnerAlarmHour").type(JsonFieldType.STRING).description("저녁 알람 시")
                                        , fieldWithPath("data.user.dinnerAlarmMinute").type(JsonFieldType.STRING).description("저녁 알람 분")
                                        , fieldWithPath("data.user.eventAlarm").type(JsonFieldType.STRING).description("이벤트 알람 여부")
                                        , fieldWithPath("data.user.serviceAlarm").type(JsonFieldType.STRING).description("서비스 알람 여부")
                                        , fieldWithPath("data.user.profileImagePath").type(JsonFieldType.STRING).description("이미지경로")
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
                        .put("/users/signUp")
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
                                        , fieldWithPath("data.idx").type(JsonFieldType.NUMBER).description("고유번호")
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
                        .post("/users/signUpDetail")
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
                                        , fieldWithPath("data.idx").type(JsonFieldType.NUMBER).description("고유번호")
                                )
                        )
                )
                .andDo(print());
    }

}
