package co.kr.heeseong.eatthis.domain.notice;

import co.kr.heeseong.eatthis.domain.notice.NoticeEntity;
import co.kr.heeseong.eatthis.domain.notice.NoticeRepository;
import co.kr.heeseong.eatthis.dto.NoticeDto;
import co.kr.heeseong.eatthis.dto.PageNavigator;
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

    public Long insertNotice(NoticeDto noticeDto) {
        return noticeRepository.save(noticeDto.toEntity()).getNoticeIdx();
    }

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
    }
}
