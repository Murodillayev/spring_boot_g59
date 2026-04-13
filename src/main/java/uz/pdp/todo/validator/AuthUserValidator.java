package uz.pdp.todo.validator;


import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.AuthUserCreateDto;

@Component
public class AuthUserValidator {
    public void validateOnCreate(AuthUserCreateDto dto) {
        if(dto == null) {
            throw new RuntimeException("Dto is null");
        }
    }
}
