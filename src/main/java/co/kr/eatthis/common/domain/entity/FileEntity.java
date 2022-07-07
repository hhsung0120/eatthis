package co.kr.eatthis.common.domain.entity;


import co.kr.eatthis.common.Enum.TableCodeType;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "file")
@AllArgsConstructor
@Getter
public class FileEntity extends TimeAndUserIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Enumerated(EnumType.STRING)
    private TableCodeType tableType;

    @Column
    private long tableIdx;

    @Column
    private String uuid;

    @Column
    private String originalFileName;

    @Column
    private String extension;

    public FileEntity() {
    }
}
