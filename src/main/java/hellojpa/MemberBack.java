package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBER_BACK")
public class MemberBack {

    @Id @GeneratedValue
    private Long id;

    //insertable, updatable : 등록, 변경 가능 여부
    // nullable(DDL) : null 값의 허용 여부를 설정한다. false로 설정하면 DDL 생성 시에 not null 제약조건이 붙는다.
    @Column(name = "name")
    private String name;

    private String username;

    private Integer age;

    // @Enumerated - JAVA에서 enum타입을 사용하기 위해 사용
    // 주의! ORDINAL 사용X, 무조건 STRING으로
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 시간을 사용하기 위해 사용
    // Temporal : DATE, TIME, TIMESTAMP
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // DB의 형식이 date
    //private LocalDate testLocalDate;

    // DB의 형식이 timestamp
    //private LocalDateTime testLocalDateTime;

    // db의 clob
    // 데이터베이스 BLOB, CLOB 타입과 매핑
    // @Lob에는 지정할 수 있는 속성이 없다.
    // 매핑하는 필드 타입이 문자면 CLOB 매핑, 나머지는 BLOB 매핑
    // CLOB: String, char[], java.sql.CLOB
    // BLOB: byte[], java.sql. BLOB
    @Lob
    private String description;

    // 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
    // 캐시에만 남아있음
//    @Transient
//    private int temp;

    //JPA는 기본생성자가 있어야함
    public MemberBack() {
    }
}
