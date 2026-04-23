package uz.pdp.todo.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.todo.config.CustomUserDetails;
import uz.pdp.todo.config.JwtUtils;
import uz.pdp.todo.config.YmlData;
import uz.pdp.todo.criteria.BaseCriteria;
import uz.pdp.todo.mapper.AuthUserMapper;
import uz.pdp.todo.model.domain.AuthUser;
import uz.pdp.todo.model.dto.*;
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
    private final YmlData ymlData;

    public AuthUserService(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, YmlData ymlData) {
        super(repository, mapper, validator);
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.ymlData = ymlData;
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

    public LoginResponse login(String username, String password) {
        AuthUser authUser = repository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if (!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        TokenDto accessToken;
        //generte token
        if (ymlData.getUserniDbDanOlibYasasinmi()) {
            accessToken = jwtUtils.generateAccessToken(authUser, Map.of(
                    "type", "access_token"
            ));
        } else {
            accessToken = jwtUtils.generateAccessToken(authUser,
                    Map.of("role", authUser.getRole(),
                            "type", "access_token",
                            "user_id", authUser.getId()));
        }

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(jwtUtils.generateRefreshToken(authUser))
                .build();

    }

    public LoginResponse refreshToken(String token) {
        Claims claims = jwtUtils.exractClaims(token);

        AuthUser authUser = repository.findByUsernameAndDeletedFalse(claims.getSubject()).orElseThrow(() -> new UsernameNotFoundException(claims.getSubject()));

        TokenDto accessToken;
        if (ymlData.getUserniDbDanOlibYasasinmi()) {
            accessToken = jwtUtils.generateAccessToken(authUser, Map.of(
                    "type", "access_token"
            ));
        } else {
            accessToken = jwtUtils.generateAccessToken(authUser,
                    Map.of("role", authUser.getRole(),
                            "type", "access_token",
                            "user_id", authUser.getId()));
        }

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(
                        TokenDto.builder()
                                .token(token)
                                .expiry(claims.getExpiration())
                                .build()
                )
                .build();
    }
}

//
//
