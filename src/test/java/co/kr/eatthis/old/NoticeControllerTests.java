//package co.kr.heeseong.eatthis;
//
//import AccountUser;
//import Jwt;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.FieldDescriptor;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static co.kr.heeseong.eatthis.ApiDocumentUtils.getDocumentRequest;
//import static co.kr.heeseong.eatthis.ApiDocumentUtils.getDocumentResponse;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//public class NoticeControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private static String token = "";
//
//    @BeforeAll
//    static void createToken() {
//        AccountUser accountUser = AccountUser.builder()
//                .idx(1)
//                .id("hhsung0120@naver.com")
//                .password("1234")
//                .nickName("nickName")
//                .build();
//        token = Jwt.createToken(accountUser);
//    }
//
//    @Test
//    public void notices() throws Exception {
//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .get("/notices/{page}", 1)
//                        .header("token", token)
//        );
//
//        FieldDescriptor[] response = new FieldDescriptor[]{
//                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("HTTP 상태 값")
//                , fieldWithPath("message").type(JsonFieldType.STRING).description("성공시 OK, 실패시 사유")
//                , fieldWithPath("data.list[].noticeIdx").type(JsonFieldType.NUMBER).description("고유 번호")
//                , fieldWithPath("data.list[].userIdx").type(JsonFieldType.NUMBER).description("작정자")
//                , fieldWithPath("data.list[].title").type(JsonFieldType.STRING).description("제목")
//                , fieldWithPath("data.list[].contents").type(JsonFieldType.STRING).description("내용")
//                , fieldWithPath("data.list[].createDate").type(JsonFieldType.STRING).description("등록 날짜")
//        };
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("notices"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , pathParameters(
//                                        parameterWithName("page").description("페이지 번호")
//                                )
//                                , responseFields(response)
//                        )
//                )
//                .andDo(print());
//    }
//
//
//}
