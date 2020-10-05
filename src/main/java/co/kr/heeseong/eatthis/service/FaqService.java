package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.domain.faq.FaqEntity;
import co.kr.heeseong.eatthis.domain.faq.FaqRepository;
import co.kr.heeseong.eatthis.dto.FaqDto;
import co.kr.heeseong.eatthis.dto.NoticeDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FaqService {

    private FaqRepository faqRepository;
    private static int pageSize = 10;

    public Long insertFaq(NoticeDto noticeDto) {
        return 3L;
    }

    public List<FaqDto> getFaqList(int page) {
        List<FaqDto> faqDtoList = new ArrayList<>();
        Page<FaqEntity> faqEntitieList = faqRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"idx"));

        for(FaqEntity faqEntity : faqEntitieList){
            FaqDto faqDto = FaqDto.builder()
                    .
        }
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
