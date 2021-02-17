package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component 붙은 거 자동으로 찾아가서 Spring Bean으로 등록시켜 줌
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = Configuration.class)
        // 자동 등록할 것 중 뺄 것을 지정해 줌
        // 앞에 우리가 설정해뒀던 AppConfig도 등록이 되니까 빼줌
)
// AppConfig에서는 의존 관계 주입을 해줬었는데 여기는 없음
// 그래서 자동 의존 관계 주입이 필요함. @Autowired
public class AutoAppConfig {

}
