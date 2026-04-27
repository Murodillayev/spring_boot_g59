package uz.pdp.todo;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service

public class A {

    @SneakyThrows
    public String m1(String data) {
        System.out.println("A m1");

        // logic
        return data.toLowerCase();
    }
}
