package uz.pdp.todo;

import org.springframework.stereotype.Service;
import uz.pdp.todo.dto.RegisterDto;
import uz.pdp.todo.dto.UserDto;

import java.util.UUID;

@Service
public class UserService {
    public UserDto register(RegisterDto dto) {
        dto.validate();
        return UserDto.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .id(UUID.randomUUID().toString())
                .build();
    }
}
