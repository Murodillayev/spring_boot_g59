package uz.pdp.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.todo.config.CustomUserDetails;
import uz.pdp.todo.config.SecurityUtils;
import uz.pdp.todo.criteria.TodoCriteria;
import uz.pdp.todo.mapper.TodoMapper;
import uz.pdp.todo.model.domain.Todo;
import uz.pdp.todo.model.dto.*;
import uz.pdp.todo.respository.AuthUserRepository;
import uz.pdp.todo.respository.TodoRepository;
import uz.pdp.todo.validator.TodoValidator;

import java.util.List;

@Service
public class TodoService
        extends AbstractService<TodoRepository, TodoMapper, TodoValidator>
        implements CRUDService<TodoCreateDto, TodoDto, TodoUpdateDto, String, TodoCriteria> {


    public TodoService(TodoRepository repository, TodoMapper mapper, TodoValidator validator) {
        super(repository, mapper, validator);
    }
    @Transactional
    public TodoDto create(TodoCreateDto dto) {
        Todo todo = mapper.fromDto(dto);
        return mapper.toDto(repository.save(todo));
    }

    @Override
    public TodoDto update(TodoUpdateDto dto, String id) {

        return null;
    }

    @Override
    public TodoDto get(String id) {


        return null;
    }

    @Override
    public PageDto<List<TodoDto>> getAll(TodoCriteria criteria) {

//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        CustomUserDetails sessionUser = (CustomUserDetails) authentication.getPrincipal();

        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<Todo> page = repository.findAllByCriteria(SecurityUtils.getCurrentUser().getId(), criteria.getIsComplete(), criteria.getSearch(), pageable);

        List<TodoDto> todos = page.getContent().stream()
                .map(mapper::toDto).toList();

        return new PageDto<>(
                page.getTotalElements(),
                page.getTotalPages(),
                todos
        );

    }

    public PageDto<List<TodoDto>> getAllDto() {

        List<TodoDto> todos = repository.findAllDto();
        return new PageDto<>(
                0l,
                0,
                todos
        );
    }

    public List<TodoProjection> getAllDtoInterface() {
        return repository.findAllDtoInterface();
    }

    @Override
    public void delete(String id) {

    }
}
