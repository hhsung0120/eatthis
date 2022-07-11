package co.kr.eatthis.common.domain.entity;


import co.kr.eatthis.common.Enum.CategoryType;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "categories")
public class CategoryEntity extends TimeAndUserIdEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column
    private String categoryName;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column
    private int orderNumber;

    public CategoryEntity() {
    }
}
