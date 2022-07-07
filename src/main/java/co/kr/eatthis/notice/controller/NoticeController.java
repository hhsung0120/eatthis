package co.kr.eatthis.notice.controller;

import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.notice.domain.model.Notice;
import co.kr.eatthis.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 공지사항
 */
@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{seq}")
    public ResponseEntity<ResponseData> getNotice(@PathVariable Long seq) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("notice", noticeService.getNotice(seq));

            return ResponseEntity.ok(new ResponseData(data));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

//    @GetMapping("/{seq}")
//    public ResponseEntity<ResponseData> noticeList(@PathVariable int page) {
//        try {
//            Map<String, Object> data = new HashMap<>();
//            data.put("list", noticeService.getNoticeList(page));
//
//            ResponseTTTData responseData = new ResponseTTTData(
//                    StatusCode.OK.getValue()
//                    , StatusCode.OK.toString()
//                    , data);
//            return ResponseEntity.ok(responseData);
//        } catch (Exception e) {
//            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
//        }
//    }

//    @GetMapping("/{page}")
//    public ResponseEntity<ResponseTTTData> noticeList(@PathVariable int page) {
//        try {
//            Map<String, Object> data = new HashMap<>();
//            data.put("list", noticeService.getNoticeList(page));
//
//            ResponseTTTData responseData = new ResponseTTTData(
//                    StatusCode.OK.getValue()
//                    , StatusCode.OK.toString()
//                    , data);
//            return ResponseEntity.ok(responseData);
//        } catch (Exception e) {
//            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
//        }
//    }

    @GetMapping("")
    public String insert() {
        Notice notice = new Notice();
//        notice.setUserIdx(1L);
//        notice.setTitle("제목이 엄청나게 길어욤ㅇㅇㅇㅇㅇㅇㅇ");
//        notice.setContents("내용입니다다다ㅏ다다ㅏ다다다다다다");

        //Long idx = noticeService.insertNotice(notice);
        return "인설트예정";//String.valueOf(idx);
    }
}
