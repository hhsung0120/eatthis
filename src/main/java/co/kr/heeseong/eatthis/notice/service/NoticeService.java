package co.kr.heeseong.eatthis.notice.service;

import co.kr.heeseong.eatthis.notice.domain.entity.NoticeEntity;
import co.kr.heeseong.eatthis.notice.domain.model.Notice;
import co.kr.heeseong.eatthis.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private static int pageSize = 10;

    public Long insertNotice(Notice noticeDto) {
        return noticeRepository.save(noticeDto.toEntity()).getNoticeIdx();
    }

    public List<Notice> getNoticeList(int page) {
        PageRequest request = PageRequest.of((page - 1), pageSize, Sort.Direction.DESC, "createDate");
        Page<NoticeEntity> noticeEntityList = noticeRepository.findAll(request);
        return new Notice().entityToVoList(noticeEntityList);
    }
}
