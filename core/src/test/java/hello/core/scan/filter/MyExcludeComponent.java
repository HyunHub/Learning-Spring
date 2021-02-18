package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
// MyExcludeComponent 가 붙은 건 ComponentScan에 제외할 거야.
}
