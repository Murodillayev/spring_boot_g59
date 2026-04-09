package uz.pdp.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping
    public String indexPage() {
        return "index";
    }
}
