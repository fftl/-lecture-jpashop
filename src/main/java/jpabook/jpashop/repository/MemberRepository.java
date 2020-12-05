package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor //MemberService 참조 (회원서비스개발 강의 17:50초 참조)
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member); // persist => insert 느낌
    }

    public Member findOne(Long id){
        return em.find(Member.class, id); //find 단건 조회
    }

    public List<Member> findAll(){    //jpql 을 사용함 테이블이 아닌 member 객체를 조회한다고 봐야 합니다. 
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();

        return result;
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
