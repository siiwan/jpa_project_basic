package hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;
    //단반향 연관관계 매핑
    @ManyToOne
    //@ManyToOne(fetch = FetchType.LAZY) //쿼리 분리돼서 실행
    @JoinColumn(name = "TEAM_ID")
    private Team team; //연관관계 주인, 외래키가 있는 곳 member가 주인임

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    //연관관계 편의메소드
    //연관관계 편의메소드나 JPA상태를 변경하는 경우 setter 대신 메소드를 만들어 사용함.
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this); //this는 나 자신 (Member)
//    }
}
