package uz.pdp.todo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo.model.dto.LoginResponse;
import uz.pdp.todo.service.AuthUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUserService service;

    public AuthController(AuthUserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        return service.login(username, password);
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(
            @RequestParam String token
    ) {
        return service.refreshToken(token);
    }
}
