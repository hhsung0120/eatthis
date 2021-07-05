package co.kr.heeseong.eatthis;

import co.kr.heeseong.eatthis.model.Faq;
import co.kr.heeseong.eatthis.service.FaqService;
import javassist.bytecode.ExceptionTable;
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

import java.util.ArrayList;
import java.util.List;

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
public class FaqController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void faqList() throws Exception {
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/faqs/{page}",1L)
        );

        FieldDescriptor[] faqs = new FieldDescriptor[]{
                fieldWithPath("dataList[].idx").type(JsonFieldType.NUMBER).description("고유 번호")
                , fieldWithPath("dataList[].categoryName").type(JsonFieldType.STRING).description("카테고리")
                , fieldWithPath("dataList[].title").type(JsonFieldType.STRING).description("제목")
                , fieldWithPath("dataList[].contents").type(JsonFieldType.STRING).description("내용")
                , fieldWithPath("dataList[].createDate").type(JsonFieldType.STRING).description("등록 날짜")
                , fieldWithPath("dataList[].lastModifiedDate").type(JsonFieldType.STRING).description("수정 날짜")
        };

        result.andExpect(status().isOk())
                .andDo(
                        document("faqs"
                                , getDocumentRequest()
                                , getDocumentResponse()
                                , pathParameters(
                                        parameterWithName("page").description("페이지 번호")
                                )
                                , responseFields(faqs)
                        )
                )
                .andDo(print());
    }



}
