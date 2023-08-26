package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // entityManagerFactory는 어플리케이션 로딩시 딱 한번만 호출하여 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // EntityManager는 트랜잭션이 일어날 때마다 사용(스레드간 공유X)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");
            //team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            //연관관계 편의 메소드
            team.addMember(member);

            //역방향(주인이 아닌 방향)만 연관관계 설정
            //team.getMembers().add(member); 연관관계 편의 메소드 생성함. member엔티티에

//            em.flush();
//            em.clear();
            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            System.out.println("=============");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("=============");

            // commit한 시점에 영속성 컨텍스트에 있는 쿼리가 실행
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
