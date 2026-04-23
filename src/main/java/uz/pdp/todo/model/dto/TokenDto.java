package uz.pdp.todo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.todo.config.CustomUserDetails;

import java.util.Date;

@Getter
@Setter
@Builder
public class TokenDto {
    private String token;
    private Date expiry;
}
