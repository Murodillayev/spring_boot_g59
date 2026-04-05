package uz.pdp.todo;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping
    public String indexPage() {
        return "index";
    }
}
