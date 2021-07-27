package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.StatusCode;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.service.NoticeService;
import co.kr.heeseong.eatthis.model.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 공지사항
 */
@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{page}")
    public ResponseEntity<ResponseData> noticeList(@PathVariable int page){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("list", noticeService.getNoticeList(page));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("")
    public String insert(){
        Notice notice = new Notice();
        notice.setUserIdx(1L);
        notice.setTitle("제목이 엄청나게 길어욤ㅇㅇㅇㅇㅇㅇㅇ");
        notice.setContents("내용입니다다다ㅏ다다ㅏ다다다다다다");

        //Long idx = noticeService.insertNotice(notice);
        return "인설트예정";//String.valueOf(idx);
    }
}
