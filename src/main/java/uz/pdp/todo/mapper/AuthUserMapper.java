package uz.pdp.todo.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.todo.model.domain.AuthUser;
import uz.pdp.todo.model.dto.AuthUserCreateDto;

@Component
public class AuthUserMapper {
    public AuthUser fromDto(AuthUserCreateDto dto) {
        AuthUser authUser = new AuthUser();
        // logic
        return authUser;
    }
}
