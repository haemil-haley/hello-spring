package hello.hellospring.service;

import hello.hellospring.domain.Member.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional // JPA를 통한 모든 테이터 변경은 트랜잭션 안에서 실행해야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * @Autowired
     *
     * 객체 생성 시점에 스프링 컨테이너에서 해당 스피링 빈을 찾아서 주입한다.
     * (생성자가 1개만 있으면 생각할 수 있다.)
     */
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        long start = System.currentTimeMillis();
        // 같은 이름이 있는 중복 회원X
        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + " ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
