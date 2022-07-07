//package co.kr.heeseong.eatthis;
//
//import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
//import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
//
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//
//public interface ApiDocumentUtils {
//
//    static OperationRequestPreprocessor getDocumentRequest() {
//        return preprocessRequest(
//                modifyUris()
//                        .scheme("http")
//                        .host("eatthis.heeseong.site")
//                        .removePort(),
//                prettyPrint());
//    }
//
//    static OperationResponsePreprocessor getDocumentResponse() {
//        return preprocessResponse(prettyPrint());
//    }
//}
