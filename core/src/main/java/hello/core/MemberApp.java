package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

       /*
        Spring으로 바꾸기 위해서 주석처리
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();*/
        // MemberService memberService = new MemberServiceImpl();

        // Spring은 모든 것이 ApplicationContext로 시작. Container. 모든 객체 관리
        // AppConfig가 가지고 있는 환경 설정 정보를 가지고 Spring이 Bean을
        // Spring Container에 객체 생성한 것 다 넣어서 관리해줌.
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        // 등록이 method 이름으로 돼서 그걸 꺼내오면 됨. 이름, 타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember : " + findMember.getName());

    }
}
