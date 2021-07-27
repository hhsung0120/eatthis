package co.kr.heeseong.eatthis;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class QuestionsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void detail() throws Exception{
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/questions/{userIdx}/{questionsIdx}", 1, 8)
        );

        FieldDescriptor[] response = new FieldDescriptor[]{
                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
                , fieldWithPath("data.idx").type(JsonFieldType.NUMBER).description("유저 고유 번호")
                , fieldWithPath("data.userIdx").type(JsonFieldType.NUMBER).description("유저 고유 번호")
                , fieldWithPath("data.userName").type(JsonFieldType.STRING).description("이름")
                , fieldWithPath("data.categoryIdx").type(JsonFieldType.NUMBER).description("카테고리 번호")
                , fieldWithPath("data.categoryName").type(JsonFieldType.STRING).description("카테고리 이름")
                , fieldWithPath("data.questions").type(JsonFieldType.STRING).description("문의 내용")
                , fieldWithPath("data.answer").type(JsonFieldType.STRING).description("답변")
                , fieldWithPath("data.status").type(JsonFieldType.STRING).description("답변 상태")
                , fieldWithPath("data.phone").type(JsonFieldType.STRING).description("연락처")
                , fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일")
                , fieldWithPath("data.createDate").type(JsonFieldType.STRING).description("문의 등록일")
                , fieldWithPath("data.lastModifiedDateToString").type(JsonFieldType.STRING).description("답변 등록일")
        };

        result.andExpect(status().isOk())
                .andDo(
                        document("questionsDetail"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("userIdx").description("고유 번호")
                                        , parameterWithName("questionsIdx").description("질문 고유 번호")
                                )
                                , responseFields(response)
                        )
                )
                .andDo(print());
    }

    @Test
    public void form() throws Exception{
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/questions/form/{userIdx}", 1)
        );

        FieldDescriptor[] response = new FieldDescriptor[]{
                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
                , fieldWithPath("data.userIdx").type(JsonFieldType.NUMBER).description("유저 고유 번호")
                , fieldWithPath("data.categoryList[].idx").type(JsonFieldType.NUMBER).description("고유 번호")
                , fieldWithPath("data.categoryList[].categoryName").type(JsonFieldType.STRING).description("카테고리 이름")
        };

        result.andExpect(status().isOk())
                .andDo(
                        document("questionsForm"
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
    public void list() throws Exception{
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/questions/{userIdx}", 1)
        );

        FieldDescriptor[] response = new FieldDescriptor[]{
                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
                , fieldWithPath("data.count").type(JsonFieldType.NUMBER).description("게시물 수량")
                , fieldWithPath("data.list[].idx").type(JsonFieldType.NUMBER).description("고유 번호")
                , fieldWithPath("data.list[].userIdx").type(JsonFieldType.NUMBER).description("작성자 번호")
                , fieldWithPath("data.list[].userName").type(JsonFieldType.STRING).description("작성자 이름")
                , fieldWithPath("data.list[].categoryIdx").type(JsonFieldType.NUMBER).description("카테고리 번호")
                , fieldWithPath("data.list[].categoryName").type(JsonFieldType.STRING).description("카테고리 이름")
                , fieldWithPath("data.list[].questions").type(JsonFieldType.STRING).description("질문")
                , fieldWithPath("data.list[].answer").type(JsonFieldType.STRING).description("답변")
                , fieldWithPath("data.list[].status").type(JsonFieldType.STRING).description("답변 상태")
                , fieldWithPath("data.list[].phone").type(JsonFieldType.STRING).description("휴대폰 번호")
                , fieldWithPath("data.list[].email").type(JsonFieldType.STRING).description("이메일")
                , fieldWithPath("data.list[].createDate").type(JsonFieldType.STRING).description("등록 날짜")
                , fieldWithPath("data.list[].lastModifiedDateToString").type(JsonFieldType.STRING).description("수정 날짜")
        };

        result.andExpect(status().isOk())
                .andDo(
                        document("questions"
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
    public void save() throws Exception{
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("categoryIdx", 4);
        user.put("questions", "test");
        user.put("phone", "01030357672");
        user.put("email", "hhsung0120@naver.com");

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/questions/form/{userIdx}", 2)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(
                        document("saveQuestions"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , requestFields(
                                        fieldWithPath("categoryIdx").type(JsonFieldType.NUMBER).description("카테고리 번호")
                                        , fieldWithPath("questions").type(JsonFieldType.STRING).description("질문")
                                        , fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대폰 번호 11자리")
                                        , fieldWithPath("email").type(JsonFieldType.STRING).description("hhsuing0120@naver.com")
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
