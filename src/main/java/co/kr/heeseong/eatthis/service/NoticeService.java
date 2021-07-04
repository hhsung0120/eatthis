package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.entity.NoticeEntity;
import co.kr.heeseong.eatthis.repository.NoticeRepository;
import co.kr.heeseong.eatthis.model.Notice;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class NoticeService {

    private NoticeRepository noticeRepository;
    private static int pageSize = 10;

    public Long insertNotice(Notice noticeDto) {
        return noticeRepository.save(noticeDto.toEntity()).getNoticeIdx();
    }

    public List<Notice> getNoticeList(int page) {
        List<Notice> noticeDtoList = new ArrayList<>();
        Page<NoticeEntity> noticeEntityList = noticeRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"createDate"));

        for(NoticeEntity noticeEntity : noticeEntityList){
            Notice notice = Notice.builder()
                    .noticeIdx(noticeEntity.getNoticeIdx())
                    .userIdx(noticeEntity.getUserIdx())
                    .title(noticeEntity.getTitle())
                    .contents(noticeEntity.getTitle())
                    .createDate(noticeEntity.getCreateDate())
                    .lastModifiedDate(noticeEntity.getLastModifiedDate())
                    .build();

            noticeDtoList.add(notice);
        }
        return noticeDtoList;
    }
}
