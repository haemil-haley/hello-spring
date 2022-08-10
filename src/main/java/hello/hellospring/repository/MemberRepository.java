package hello.hellospring.repository;

import hello.hellospring.domain.Member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); // 반환값이 null일수 있는 상황에서 null처리를 Option에 감싸서 리턴하는 방식을 선호하는 추세이다.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
