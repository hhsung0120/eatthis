package co.kr.heeseong.eatthis.domain.faq;

import co.kr.heeseong.eatthis.dto.FaqDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class FaqService {

    private FaqRepository faqRepository;
    private static int pageSize = 10;

    /**
     * 자주묻는 질문 리스트
     * @param page
     * @return List<FaqDto>
     * @throws Exception
     */
    public List<FaqDto> getFaqList(int page) throws Exception{
        List<FaqDto> faqDtoList = new ArrayList<>();
        Page<FaqEntity> faqEntityList = faqRepository.findAll(PageRequest.of((page-1), pageSize, Sort.Direction.DESC,"idx"));

        for(FaqEntity faqEntity : faqEntityList){
            FaqDto faqDto = FaqDto.builder()
                    .idx(faqEntity.getIdx())
                    .title(faqEntity.getTitle())
                    .categoryName(faqEntity.getFaqCategoryEntity().getCategoryName())
                    .contents(faqEntity.getContents())
                    .createDate(faqEntity.getCreateDate())
                    .lastModifiedDate(faqEntity.getLastModifiedDate())
                    .build();

            faqDtoList.add(faqDto);
        }

        return faqDtoList;
    }

    public Map<String, Object> getFaqListResult(int page){
        Map<String, Object> data = new LinkedHashMap<>();
        try{
            data.put("faqList", this.getFaqList(page));
        }catch (Exception e){
            log.info("getFaqListResult exception {}", e.getMessage());
            data.clear();
        }
        return data;
    }
}
