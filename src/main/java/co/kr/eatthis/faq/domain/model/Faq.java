package co.kr.eatthis.faq.domain.model;

import co.kr.eatthis.faq.domain.entity.FaqEntity;
import co.kr.eatthis.notice.domain.entity.NoticeEntity;
import co.kr.eatthis.notice.domain.model.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class Faq {

    private Long seq;
    private Long categorySeq;
    private String title;
    private String contents;

    public Faq() {
    }

    public Faq(FaqEntity faqEntity) {
        this.seq = faqEntity.getSeq();
        this.categorySeq = faqEntity.getCategorySeq();
        this.title = faqEntity.getTitle();
        this.contents = faqEntity.getContents();
    }

    public static List<Faq> entityToList(Page<FaqEntity> faqEntityList){
        return faqEntityList.getContent().stream()
                .map(entity -> new Faq(entity))
                .collect(toList());
    }
}
