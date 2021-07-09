package co.kr.heeseong.eatthis;

import co.kr.heeseong.eatthis.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static co.kr.heeseong.eatthis.ApiDocumentUtils.getDocumentRequest;
import static co.kr.heeseong.eatthis.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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

    @Test
    public void login() throws Exception{

        //json 으로 보내야함
        //받을 때 객체로 안받도록 다시 설계....
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/users/login")
                        .param("id","hhsung0120@naver.com")
                        .param("password","12345")
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("login"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("메시지")
                                        , fieldWithPath("data").type(JsonFieldType.STRING).description("추후 메인 매장 데이터 응답 예정")
                                )
                        )
                )
                .andDo(print());
    }


    @Test
    public void users() throws Exception {
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/users/{idx}",1L)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("users"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("idx").description("고유 번호")
                                )
                                , responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("메시지")
                                        , fieldWithPath("data.idx").type(JsonFieldType.NUMBER).description("고유 번호")
                                        , fieldWithPath("data.id").type(JsonFieldType.STRING).description("아이디")
                                        , fieldWithPath("data.nickName").type(JsonFieldType.STRING).description("닉네임")
                                        , fieldWithPath("data.gender").type(JsonFieldType.STRING).description("성별")
                                        , fieldWithPath("data.birthday").type(JsonFieldType.STRING).description("생일")
                                        , fieldWithPath("data.lunchAlarm").type(JsonFieldType.STRING).description("점심 알람 여부")
                                        , fieldWithPath("data.lunchAlarmHour").type(JsonFieldType.NUMBER).description("점심 알람 시")
                                        , fieldWithPath("data.lunchAlarmMinute").type(JsonFieldType.NUMBER).description("점심 알람 분")
                                        , fieldWithPath("data.dinnerAlarm").type(JsonFieldType.STRING).description("저녁 알람 여부")
                                        , fieldWithPath("data.dinnerAlarmHour").type(JsonFieldType.NUMBER).description("저녁 알람 시")
                                        , fieldWithPath("data.dinnerAlarmMinute").type(JsonFieldType.NUMBER).description("저녁 알람 분")
                                        , fieldWithPath("data.eventAlarm").type(JsonFieldType.STRING).description("이벤트 알람 여부")
                                        , fieldWithPath("data.serviceAlarm").type(JsonFieldType.STRING).description("서비스 알람 여부")
                                        , fieldWithPath("data.profileImagePath").type(JsonFieldType.STRING).description("프로필 이미지 경로")
                                )
                        )
                )
                .andDo(print());
    }



}
