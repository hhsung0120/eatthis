package co.kr.eatthis.notice.domain.model;

import co.kr.eatthis.common.util.StringUtils;
import co.kr.eatthis.notice.domain.entity.NoticeEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Notice {

    private Long seq;
    private String title;
    private String contents;
    private String createdId;
    private String createdDatetime;

    public Notice() {
        this.seq = 0L;
        this.title = "";
        this.contents = "";
        this.createdId = "";
        this.createdDatetime = "";
    }

    public NoticeEntity toEntity() {
        return NoticeEntity.insertForNoticeEntity()
                .seq(seq)
                .title(title)
                .contents(contents)
                .build();
    }

    public Notice(NoticeEntity noticeEntity) {
        this.seq = noticeEntity.getSeq();
        this.title = noticeEntity.getTitle();
        this.contents = noticeEntity.getContents();
        this.createdId = noticeEntity.getCreatedId();
        this.createdDatetime = StringUtils.localDateTimeToString(noticeEntity.getCreatedDatetime());
    }


//    @Builder
//    public Notice(Long noticeIdx, Long userIdx, String title, String contents, LocalDateTime createDate) {
//        this.noticeIdx = noticeIdx;
//        this.userIdx = userIdx;
//        this.title = title;
//        this.contents = contents;
//        this.createDate = createDate == null ? "" : createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//    }
//
//    public List<Notice> entityToVoList(Page<NoticeEntity> noticeEntityList){
//        return noticeEntityList.stream()
//                .map(list -> Notice.builder()
//                        .noticeIdx(list.getNoticeIdx())
//                        .userIdx(list.getUserIdx())
//                        .title(list.getTitle())
//                        .contents(list.getTitle())
//                        //.createDate(list.getCreateDate())
//                        .build())
//                .collect(toList());
//    }

}
