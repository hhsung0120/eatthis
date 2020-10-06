package co.kr.heeseong.eatthis.domain.faq;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "faq_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String categoryName;

}
