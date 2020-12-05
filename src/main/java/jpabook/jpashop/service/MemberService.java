package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 뺴먹으면 안됩니다. readOnly 는 조회 일때만 사용
//@AllArgsConstructor //lombok 아래와 같은 MemberService 생성자를 만들어줍니다.
@RequiredArgsConstructor //lombok 아래와 같은 MemberService 생성자를 만들어 줍니다 다만 final 이 있는 것의 생성자만 만듬니다.
public class MemberService {

    private final MemberRepository memberRepository; //final 컴파일 시점에 문제를 체크할 수 있따.

//    @Autowired //스프링 빈에 등록되어 있는 리포지토리를 주입해줍니다. //생성자가 하나일 경우 생략할 수 있습니다.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**회원가입 */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //최소한의 확인을 위해 아이디를 가져와준다.
    }

    private void validateDuplicateMember(Member member){
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**전체회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**회원 조회 */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId); //find 어쩌고는 다 repository 에서 만들어 놓은 것.
    }
}
