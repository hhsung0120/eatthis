package co.kr.heeseong.eatthis;

import co.kr.heeseong.eatthis.model.AccountUser;
import co.kr.heeseong.eatthis.util.Jwt;
import org.junit.jupiter.api.BeforeAll;
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
@AutoConfigureRestDocs
public class StoreControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private static String token = "";

    @BeforeAll
    static void createToken(){
        AccountUser accountUser = AccountUser.builder()
                .idx(1)
                .id("hhsung0120@naver.com")
                .password("1234")
                .nickName("nickName")
                .build();
        token = Jwt.createToken(accountUser);
    }

    @Test
    public void stores() throws Exception {
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/stores/{locationX}/{locationY}",1, 1)
                        .header("token", token)
        );

        FieldDescriptor[] response = new FieldDescriptor[]{
                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
                , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
                , fieldWithPath("data.list[].storeIdx").type(JsonFieldType.NUMBER).description("고유 번호")
                , fieldWithPath("data.list[].storeId").type(JsonFieldType.NUMBER).description("상점아이디")
                , fieldWithPath("data.list[].category").type(JsonFieldType.STRING).description("카테고리")
                , fieldWithPath("data.list[].storeName").type(JsonFieldType.STRING).description("상점이름")
                , fieldWithPath("data.list[].locationX").type(JsonFieldType.STRING).description("X좌표")
                , fieldWithPath("data.list[].locationY").type(JsonFieldType.STRING).description("Y좌표")
                , fieldWithPath("data.list[].createDate").type(JsonFieldType.STRING).description("등록 날짜")
                , fieldWithPath("data.list[].lastModifiedDate").type(JsonFieldType.STRING).description("수정 날짜")
        };

        result.andExpect(status().isOk())
                .andDo(
                        document("stores"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("locationX").description("X 좌표")
                                        , parameterWithName("locationY").description("Y 좌표")
                                )
                                , responseFields(response)
                        )
                )
                .andDo(print());
    }
}
