package hello.core.singleton;

public class SingletonService {
    // 자기 자신을 내부에 private static으로 하나 가지고 있음-> 자바 뜰 때 생성돼서 올라감
    // 자기 객체 생성해서 instance에 넣어둠
    private static final SingletonService instance = new SingletonService();

    // 조회할 때. instance 참조 호출할 수 잇는 것은 얘밖에 없음
    public static SingletonService getInstance() {
        return instance;
    }

    // 다른 곳에서의 new ... 생성을 막기 위한 private 생성자
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출출");
   }

}
