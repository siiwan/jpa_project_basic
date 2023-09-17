package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    //OneToOne 양방향 매핑
    //@OneToOne(mappedBy = "locker")
    //private Member member;
}
