package uz.pdp.todo.model;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserCreate {
    private String phone;
    private String password;
    private String fullName;
}
