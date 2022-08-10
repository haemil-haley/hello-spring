package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /*
    // JdbcMemberRepository Bean설정
    private final DataSource dataSource; // 데이터베이스 커넥션을 획득할 떄 사용하는 객체

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService MemberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
    */

    /*
    // JdbcTemplateMemberRepository Bean설정
    private final DataSource dataSource; // 데이터베이스 커넥션을 획득할 떄 사용하는 객체

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService MemberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
    */

    /*
    // JpaMemberRepository Bean설정
    private final EntityManager em;
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService MemberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
    */

    // SpringDataJpaMemberRepository
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    // MemberService에서 @Autowired 해서 사용하는편이 좋다.
    @Bean
    public MemberService MemberService() {
        return new MemberService(memberRepository);
    }
    */

    /**
     * TimeTraceAop 에서 @Component 로 직접 빈으로 등록해도 되지만,
     * 특별한 기능을 수행하는 것은 명시적으로 등록해서 쓰도록 한다.
     */
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}
