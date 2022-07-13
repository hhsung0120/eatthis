package co.kr.eatthis.notice.controller;

import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public ResponseEntity<ResponseData> getNoticeList(
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {

        try {
            return ResponseEntity.ok(new ResponseData(noticeService.getNoticeList(page, pageSize)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }
}
