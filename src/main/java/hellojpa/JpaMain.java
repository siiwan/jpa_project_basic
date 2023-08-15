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
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            //commit할 때 update
            findMember.setName("HelloA");

            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
