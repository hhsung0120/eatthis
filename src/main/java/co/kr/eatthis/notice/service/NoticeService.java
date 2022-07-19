package co.kr.eatthis.notice.service;

import co.kr.eatthis.common.domain.model.PageNavigator;
import co.kr.eatthis.common.util.LogUtils;
import co.kr.eatthis.notice.domain.entity.NoticeEntity;
import co.kr.eatthis.notice.domain.model.Notice;
import co.kr.eatthis.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Notice getNotice(Long seq) {
        try {
            return noticeRepository.findById(seq)
                    .map(data -> new Notice(data))
                    .orElse(new Notice());
        } catch (Exception e) {
            LogUtils.errorLog("getNotice exception", "seq", seq, e);
            throw new IllegalArgumentException("getNotice exception - seq : " + seq);
        }
    }

    public Map<String, Object> getNoticeList(PageNavigator pageNavigator) {
        log.info("getNoticeList pageNavigator : {}", pageNavigator);

        int page = (pageNavigator.getPage() -1);
        int pageSize = pageNavigator.getPageSize();

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.Direction.DESC, "createdDatetime");
        try {
            Page<NoticeEntity> noticeEntityList = noticeRepository.findAll(pageRequest);

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("list", Notice.entityToList(noticeEntityList));
            result.put("totalCount", noticeEntityList.getTotalElements());
            result.put("totalPageSize", noticeEntityList.getTotalPages());
            return result;
        } catch (Exception e) {
            LogUtils.errorLog("getNoticeList exception", "page", page, "pageSize", pageSize, e);
            throw new IllegalArgumentException("getNoticeList exception : " + e.getMessage());
        }
    }
}
