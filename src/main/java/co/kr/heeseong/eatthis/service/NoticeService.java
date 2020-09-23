package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.domain.notice.NoticeEntity;
import co.kr.heeseong.eatthis.domain.notice.NoticeRepository;
import co.kr.heeseong.eatthis.dto.NoticeDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class NoticeService {

    private NoticeRepository noticeRepository;

    public Long insertNotice(NoticeDto noticeDto) {
        return noticeRepository.save(noticeDto.toEntity()).getNoticeIdx();
    }

    public List<NoticeDto> getNoticeList() {
        List<NoticeDto> noticeDtoList = new ArrayList<>();
        List<NoticeEntity> noticeEntityList = noticeRepository.findAllDesc();

        for(NoticeEntity noticeEntity : noticeEntityList){
            NoticeDto noticeDto = NoticeDto.builder()
                    .noticeIdx(noticeEntity.getNoticeIdx())
                    .userIdx(noticeEntity.getUserIdx())
                    .title(noticeEntity.getTitle())
                    .contents(noticeEntity.getTitle())
                    .regDate(noticeEntity.getRegDate())
                    .modDate(noticeEntity.getModDate())
                    .build();

            noticeDtoList.add(noticeDto);
        }
        return noticeDtoList;
    }
}
