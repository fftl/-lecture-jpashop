package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest //스프링부트 환경에서 테스트를 돌리기 위해서 필요
@Transactional //이게 있어야 테스트 후 롤백이 가능
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false) // false를 해놓으면 실제로 코드를 볼 수 있다.
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
         assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
//        try{ 이 위의 expected = IllegalStateException.class 덕분에 생략할 수 있게됩니다.
            memberService.join(member2); //여기서 예외가 발생해야 합니다.
//        }catch (IllegalStateException e){
//            return;
//        }

        //then
        fail("예외가 발생해야 한다."); //fail 이 실행되면 일단 무언가 잘못됨을 선언해 줄수 있습니다. 여기에 오면 안된다는것.

    }

}