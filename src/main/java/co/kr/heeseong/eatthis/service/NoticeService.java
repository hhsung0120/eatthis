package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.entity.NoticeEntity;
import co.kr.heeseong.eatthis.model.Notice;
import co.kr.heeseong.eatthis.repository.NoticeRepository;
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
        Page<NoticeEntity> noticeEntityList = noticeRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"createDate"));
        return noticeEntityList.stream()
                                .map(list -> Notice.builder()
                                            .noticeIdx(list.getNoticeIdx())
                                            .userIdx(list.getUserIdx())
                                            .title(list.getTitle())
                                            .contents(list.getTitle())
                                            .createDate(list.getCreateDate())
                                            .build())
                                .collect(toList());
    }
}
