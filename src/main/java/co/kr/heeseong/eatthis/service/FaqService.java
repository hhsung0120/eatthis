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
        Page<FaqEntity> faqEntityList = faqRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"idx"));

        for(FaqEntity faqEntity : faqEntityList){
            FaqDto faqDto = FaqDto.builder()
                    .idx(faqEntity.getIdx())
                    .categoryIdx(faqEntity.getCategoryIdx())
                    .title(faqEntity.getTitle())
                    .contents(faqEntity.getContents())
                    .createDate(faqEntity.getCreateDate())
                    .lastModifiedDate(faqEntity.getLastModifiedDate())
                    .build();

            faqDtoList.add(faqDto);
        }

        return faqDtoList;
    }
}
