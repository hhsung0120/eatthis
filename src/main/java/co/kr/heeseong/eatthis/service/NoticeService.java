package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.domain.notice.NoticeRepository;
import co.kr.heeseong.eatthis.dto.NoticeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NoticeService {

    private NoticeRepository noticeRepository;

    public Long insertNotice(NoticeDto noticeDto) {
        return noticeRepository.save(noticeDto.toEntity()).getNoticeIdx();
    }
}
