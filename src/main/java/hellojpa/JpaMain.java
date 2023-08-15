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
            //비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속 상태(db에 저장이 되지 않음)
            System.out.println("====Before");
            em.persist(member);
            //em.detach(member); //준영속 상태 (영속성컨텍스트에서 지움)
            //em.remove(member); // 실제 객체를 삭제
            System.out.println("====After");
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
