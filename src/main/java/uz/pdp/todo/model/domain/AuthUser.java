package uz.pdp.todo.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.todo.model.enums.AuthRole;

import java.util.List;

@Getter
@Setter
@Entity
public class AuthUser extends BaseEntity {
    private String fullName;
    private String email;
    private String phone;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthRole role;

}
