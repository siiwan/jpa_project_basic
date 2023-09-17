package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        // entityManagerFactory는 어플리케이션 로딩시 딱 한번만 호출하여 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // EntityManager는 트랜잭션이 일어날 때마다 사용(스레드간 공유X)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());

            logic(m1, m2);


            // commit한 시점에 영속성 컨텍스트에 있는 쿼리가 실행
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        // 타입비교 하기 instanceof으로 하기
        System.out.println("(m1.getClass() == m2.getClass()) = " + (m1.getClass() == m2.getClass()));
        System.out.println("(m1 instanceof Member) = " + (m1 instanceof Member));
        System.out.println("(m2 instanceof Member) = " + (m2 instanceof Member));
    }

}
