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
    public void saveQuestions() throws Exception{
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
