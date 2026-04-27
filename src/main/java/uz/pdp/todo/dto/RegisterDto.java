package uz.pdp.todo.dto;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.todo.exception.BadRequestException;

@Getter
@Setter
public class RegisterDto {
    private String username;
    private String password;
    private String email;


    public void validate() {
        if (username == null || username.isEmpty()) {
            throw new BadRequestException("Username xato", "kz");
        }
        if (password == null || password.isEmpty()) {
            throw new BadRequestException("san beragan [%s] kuchusiz ".formatted(this.password));
        }
        if (email == null || email.isEmpty()) {
            throw new BadRequestException("Email xato");
        }

        if (password.length() < 8) {
            throw new BadRequestException("Password too short");
        }
        if (password.matches(".*\\d.*")) {
            throw new BadRequestException("Password contains dash");
        }
    }
}
