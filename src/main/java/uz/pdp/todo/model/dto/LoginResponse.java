package uz.pdp.todo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
@Builder
public class LoginResponse {

    private TokenDto accessToken; // 30 min
    private TokenDto refreshToken; // 3 kun
}
