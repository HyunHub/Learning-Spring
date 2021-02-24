package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final MyLogger myLogger; // 의존관계 주입

    // MyLogger를 주입받는 것이 아니라 DL 할 수 있는 게 주입됨.
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        // HttpServletRequest 고객 요청 정보 받을 수 있음


        // 고객이 어떤 URL로 요청했는지 알 수 있음
       String requestURL = request.getRequestURL().toString();

        MyLogger myLogger = myLoggerProvider.getObject();

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller log");

        logDemoService.logic("testID");

        return "OK";

    }
}
