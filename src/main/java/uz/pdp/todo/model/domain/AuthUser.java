package uz.pdp.todo.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.todo.model.enums.AuthRole;

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
