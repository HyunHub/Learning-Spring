package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class HelloLombok {

    private int age;
    private String name;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(10);
        System.out.println("helloLombok = " + helloLombok.getAge());
        System.out.println("helloLombok = " + helloLombok);
    }

}
