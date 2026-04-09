package uz.pdp.todo.service;

import uz.pdp.todo.criteria.BaseCriteria;
import uz.pdp.todo.mapper.AuthUserMapper;
import uz.pdp.todo.model.dto.AuthUserCreateDto;
import uz.pdp.todo.model.dto.AuthUserDto;
import uz.pdp.todo.model.dto.AuthUserUpdateDto;
import uz.pdp.todo.respository.AuthUserRepository;
import uz.pdp.todo.validator.AuthUserValidator;

import java.util.List;

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
    public List<AuthUserDto> getAll(BaseCriteria criteria) {
        return List.of();
    }

    @Override
    public void delete(String id) {

    }
}
