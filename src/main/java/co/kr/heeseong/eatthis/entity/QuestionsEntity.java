package co.kr.heeseong.eatthis.entity;


import co.kr.heeseong.eatthis.Enum.QuestionsStatusType;
import co.kr.heeseong.eatthis.entity.common.TimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "questions")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionsEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column
    private long userIdx;

    @Column
    private long categoryIdx;

    @Column
    private String questions;

    @Column
    private String answer;

    @Enumerated(EnumType.STRING)
    private QuestionsStatusType status;

    @Column
    private String phone;

    @Column
    private String email;

    @Builder
    public QuestionsEntity(long idx, long userIdx, String questions, String phone, String email, long categoryIdx) {
        this.idx = idx;
        this.userIdx = userIdx;
        this.questions = questions;
        this.answer = "";
        this.status = QuestionsStatusType.WAITING;
        this.phone = phone;
        this.email = email;
        this.categoryIdx= categoryIdx;
    }
}
