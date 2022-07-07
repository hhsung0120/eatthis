package co.kr.eatthis.notice.service;

import co.kr.eatthis.common.util.LogUtils;
import co.kr.eatthis.notice.domain.entity.NoticeEntity;
import co.kr.eatthis.notice.domain.model.Notice;
import co.kr.eatthis.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private static int pageSize = 10;

//    public Long insertNotice(Notice noticeDto) {
//        return noticeRepository.save(noticeDto.toEntity()).getNoticeIdx();
//    }

    public Notice getNotice(Long seq) {
        try {
            return noticeRepository.findById(seq)
                    .map(data -> new Notice(data))
                    .orElse(new Notice());
        } catch (Exception e) {
            LogUtils.errorLog("getNotice exception", "seq", seq, e);
            throw new IllegalArgumentException("getNotice exception - seq : " + seq);
        }
    }

    public List<Notice> getNoticeList(int page) {
//        PageRequest request = PageRequest.of((page - 1), pageSize, Sort.Direction.DESC, "createDate");
//        Page<NoticeEntity> noticeEntityList = noticeRepository.findAll(request);
//        return new Notice().entityToVoList(noticeEntityList);
        return new ArrayList<>();
    }
}
