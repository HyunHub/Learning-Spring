package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component 붙은 거 자동으로 찾아가서 Spring Bean으로 등록시켜 줌
        // basePackages = "hello.core.member", // 탐색할 패키지 시작 위치 지정해줄 수 있음
        // 지정해주지 않으면 default인데
        // ComponentScan을 붙인 이 클래스의 패키지 hello.core 여기에서부터 시작해서
        // 하위까지 다 뒤짐

        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = Configuration.class)
        // 자동 등록할 것 중 뺄 것을 지정해 줌
        // 앞에 우리가 설정해뒀던 AppConfig도 등록이 되니까 빼줌
)
// AppConfig에서는 의존 관계 주입을 해줬었는데 여기는 없음
// 그래서 자동 의존 관계 주입이 필요함. @Autowired
public class AutoAppConfig {

    /*@Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
