package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.dto.NoticeDto;
import co.kr.heeseong.eatthis.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 공지사항
 */
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list/{page}")
    public Map<String, Object> noticeList(@PathVariable int page){
        Map<String, Object> result = new HashMap<>();
        result.put("noticeList", noticeService.getNoticeList(page));
        return result;
    }

    @GetMapping("/insert")
    public String insert(){
        NoticeDto notice = new NoticeDto();
        notice.setUserIdx(1L);
        notice.setTitle("제목이 엄청나게 길어욤ㅇㅇㅇㅇㅇㅇㅇ");
        notice.setContents("내용입니다다다ㅏ다다ㅏ다다다다다다");

        Long idx = noticeService.insertNotice(notice);
        return String.valueOf(idx);
    }
}
