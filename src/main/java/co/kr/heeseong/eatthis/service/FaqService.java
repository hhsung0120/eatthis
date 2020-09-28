package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.dto.NoticeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FaqService {

    public Long insertFaq(NoticeDto noticeDto) {
        return 3L;
    }
/*
    public List<NoticeDto> getNoticeList(int page) {
        List<NoticeDto> noticeDtoList = new ArrayList<>();
        Page<NoticeEntity> noticeEntityList = noticeRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"createDate"));

        for(NoticeEntity noticeEntity : noticeEntityList){
            NoticeDto noticeDto = NoticeDto.builder()
                    .noticeIdx(noticeEntity.getNoticeIdx())
                    .userIdx(noticeEntity.getUserIdx())
                    .title(noticeEntity.getTitle())
                    .contents(noticeEntity.getTitle())
                    .createDate(noticeEntity.getCreateDate())
                    .lastModifiedDate(noticeEntity.getLastModifiedDate())
                    .build();

            noticeDtoList.add(noticeDto);
        }
        return noticeDtoList;
    }*/
}
