package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
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

    public AuthUserService(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator) {
        super(repository, mapper, validator);
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
}
