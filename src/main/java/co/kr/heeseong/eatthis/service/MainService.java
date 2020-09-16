package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.domain.notice.NoticeEntity;
import co.kr.heeseong.eatthis.domain.notice.NoticeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class MainService {

    private NoticeRepository noticeRepository;

    /**
     * 메인 리스트
     * @return Map<String, Object>
     */
    public Map<String, Object> getMainList() {
        Map<String, Object> result = new HashMap<>();

        List<NoticeEntity> noticeList = noticeRepository.findAll();

        System.out.println(noticeList.toString());
        result.put("noticeList", noticeList);

        return result;
    }
}
