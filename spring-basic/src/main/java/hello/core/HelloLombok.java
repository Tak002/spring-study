package hello.core;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
    private String name;
    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        System.out.println(helloLombok.getName());
    }
}
