package uz.pdp.todo.service;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import uz.pdp.todo.repository.AuthUserRepository;
import uz.pdp.todo.model.AuthUser;
import uz.pdp.todo.model.AuthUserCreate;
import uz.pdp.todo.model.RegisterDto;
import uz.pdp.todo.model.UserStatus;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class AuthUserService {
    private final CacheService cache;
    private final AuthUserRepository authUserRepository;

    public AuthUserService(CacheService cache, AuthUserRepository authUserRepository) {
        this.cache = cache;
        this.authUserRepository = authUserRepository;
    }

    public RegisterDto register(AuthUserCreate dto) {
        AuthUser authUser = new AuthUser();
        authUser.setFullName(dto.getFullName());
        authUser.setPassword(dto.getPassword());
        authUser.setStatus(UserStatus.PENDING);
        authUser.setPhone(dto.getPhone());
        AuthUser save = authUserRepository.save(authUser);


        String code = generateCode();
        cache.putConfirmCode(code, save.getId());

        return RegisterDto.builder()
                .seconds(20L)
                .message(dto.getPhone() + " raqamga tasdiqlash kodi yuborildi.")
                .build();
    }


    public @Nullable AuthUser confirmCode(String code) {
        Long userId = cache.checkCode(code);

        if (userId == null) {
            throw new RuntimeException("Invalid code");
        }

        AuthUser authUser = authUserRepository.findById(userId).orElseThrow();

        authUser.setStatus(UserStatus.ACTIVE);
        return authUserRepository.save(authUser);
    }


    private String generateCode() {
        int code = ThreadLocalRandom.current().nextInt(1000, 10000);
        String codeString = String.valueOf(code);

        if (cache.checkCode(codeString) == null) {
            return codeString;
        }
        return generateCode();
    }


}
