package uz.pdp.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


    @PostMapping("/create-api")
    public ResponseEntity<?> createApi(@RequestBody Object dto){

        // /api/v1/product
        // Method get
        // logic sql: select * from product; // insert into // update // delete from
        return null;
    }

    @GetMapping("/api/{api-path}")
    public ResponseEntity<Object> call(){
        return null;
    }

    @PostMapping("/api/{api-path}")
    public ResponseEntity<?> call2(){
        return null;
    }

    @PutMapping("/api/{api-path}")
    public ResponseEntity<?> call3(){
        return null;
    }

    @DeleteMapping("/api/{api-path}")
    public ResponseEntity<?> call4(){
        return null;
    }
}
