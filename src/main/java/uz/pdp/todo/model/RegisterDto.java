package uz.pdp.todo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterDto {
    private Long seconds;
    public String message;
}
