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
    private Team team;

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
}
