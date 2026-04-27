package uz.pdp.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {
    private final A a;
    private final B b;
    private final C c;


    @GetMapping("/test")
    public String test() {
        String aV = a.m1("Muhammadkomil");
        b.m1("Muhammadkomil");
        c.m1("Muhammadkomil");

        return aV;
    }

    // Aspect, Advice, pointcut, JoinPoint
}
