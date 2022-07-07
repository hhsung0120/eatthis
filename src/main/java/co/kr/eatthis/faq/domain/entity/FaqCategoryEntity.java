package co.kr.eatthis.faq.domain.entity;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@Table(name = "faq_category")
public class FaqCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column
    private String categoryName;

    @Column
    private int order;

    @OneToMany(mappedBy = "faqCategoryEntity")
    private Set<FaqEntity> faqEntityList = new HashSet<>();

    public FaqCategoryEntity() {
    }
}
