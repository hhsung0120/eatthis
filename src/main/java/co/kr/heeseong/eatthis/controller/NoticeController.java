package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.dto.NoticeDto;
import co.kr.heeseong.eatthis.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public List<NoticeDto> noticeList(){
        return noticeService.getNoticeList();
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
