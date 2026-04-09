package uz.pdp.todo.model.domain;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.todo.model.enums.AuthRole;

@Getter
@Setter
public class AuthUser extends BaseEntity {
    private String fullName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private AuthRole role;

}
