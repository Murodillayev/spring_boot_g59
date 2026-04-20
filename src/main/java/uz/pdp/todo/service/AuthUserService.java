package uz.pdp.todo.service;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.todo.config.CustomUserDetails;
import uz.pdp.todo.config.JwtUtils;
import uz.pdp.todo.criteria.BaseCriteria;
import uz.pdp.todo.mapper.AuthUserMapper;
import uz.pdp.todo.model.domain.AuthUser;
import uz.pdp.todo.model.dto.AuthUserCreateDto;
import uz.pdp.todo.model.dto.AuthUserDto;
import uz.pdp.todo.model.dto.AuthUserUpdateDto;
import uz.pdp.todo.model.dto.PageDto;
import uz.pdp.todo.respository.AuthUserRepository;
import uz.pdp.todo.validator.AuthUserValidator;

import java.util.List;
import java.util.Map;

@Service
public class AuthUserService
        extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator>
        implements CRUDService<
        AuthUserCreateDto,
        AuthUserDto,
        AuthUserUpdateDto,
        String,
        BaseCriteria> {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthUserService(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        super(repository, mapper, validator);
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public UserDetails findByUsername(String username) throws UsernameNotFoundException {

        // load from db by username
        AuthUser authUser = repository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> new UsernameNotFoundException(username));
        // return user details
        return CustomUserDetails.builder()
                .id(authUser.getId())
                .username(authUser.getUsername())
                .password(authUser.getPassword())
                .role(authUser.getRole())
                .build();
    }

    @Override
    public AuthUserDto create(AuthUserCreateDto dto) {
        validator.validateOnCreate(dto);


        AuthUser authUser = mapper.fromDto(dto);
        return null;
    }

    @Override
    public AuthUserDto update(AuthUserUpdateDto dto, String id) {
        return null;
    }

    @Override
    public AuthUserDto get(String id) {
        return null;
    }

    @Override
    public PageDto<List<AuthUserDto>> getAll(BaseCriteria criteria) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    public String login(String username, String password) {
        AuthUser authUser = repository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if (!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        //generte token
        return jwtUtils.generateToken(authUser,
                Map.of("role", authUser.getRole(),
                        "user_id", authUser.getId()
                ));
    }


}

//
