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

//            //영속 엔티티의 동일성 보장 시작
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//            System.out.println("(findMember1 == findMember2) = " + (findMember1 == findMember2));
//            //영속 엔티티의 동일성 보장 종료

            // 영속
//            Member member1 = new Member(350L, "AAA");
//            Member member2 = new Member(360L, "BBB");
//
//            em.persist(member1);
//            em.persist(member2);

            // update 시작
//            Member member = em.find(Member.class, 360L);
//            member.setName("이름변경");

            //아래 코드를 사용할 필요가 없다.
            //em.persist(member);
            // update 종료

            Member member1 = em.find(Member.class, 460L);
            member1.setName("member");
            em.detach(member1);

            System.out.println("============================");

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
