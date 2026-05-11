package uz.pdp.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.AuthUser;
import uz.pdp.todo.model.AuthUserCreate;
import uz.pdp.todo.model.RegisterDto;
import uz.pdp.todo.service.AuthUserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService service;

    @PostMapping
    public ResponseEntity<RegisterDto> register(@RequestBody AuthUserCreate dto) {
        return new ResponseEntity<>(service.register(dto), HttpStatus.OK);
    }

    @PutMapping("/confirm-code")
    public ResponseEntity<AuthUser> confirmCode(@RequestParam String code) {
        return ResponseEntity.ok(service.confirmCode(code));
    }


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> errorHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
