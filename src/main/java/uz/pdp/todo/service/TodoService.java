package uz.pdp.todo.service;

import org.springdoc.core.service.OperationService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.todo.config.SecurityUtils;
import uz.pdp.todo.criteria.TodoCriteria;
import uz.pdp.todo.mapper.TodoMapper;
import uz.pdp.todo.model.domain.Todo;
import uz.pdp.todo.model.dto.*;
import uz.pdp.todo.respository.TodoRepository;
import uz.pdp.todo.validator.TodoValidator;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService
        extends AbstractService<TodoRepository, TodoMapper, TodoValidator>
        implements CRUDService<TodoCreateDto, TodoDto, TodoUpdateDto, String, TodoCriteria> {

    private final CacheService cacheService;

    public TodoService(TodoRepository repository, TodoMapper mapper, TodoValidator validator, CacheService cacheService) {
        super(repository, mapper, validator);
        this.cacheService = cacheService;
    }

    @Transactional
    public TodoDto create(TodoCreateDto dto) {
        Todo todo = mapper.fromDto(dto);
        return mapper.toDto(repository.save(todo));
    }

    @Override
//    @CachePut(value = "todos", key = "#id")
    @CacheEvict(value = "todos", allEntries = true)
    public TodoDto update(TodoUpdateDto dto, String id) {
        Todo todo = validator.existsAndGet(id);
        mapper.fromDto(todo, dto);
        return mapper.toDto(repository.save(todo));
    }

    @Override
    @Cacheable(value = "todos", key = "#id")
    public TodoDto get(String id) {
        Todo todo = validator.existsAndGet(id);
        return mapper.toDto(todo);
    }

    // TodoServive: [todo1,todo2]
    @Override
//    @Cacheable(value = "todos", key = "#criteria.toString()")
    public PageDto<List<TodoDto>> getAll(TodoCriteria criteria) throws InterruptedException {
        PageDto<List<TodoDto>> cashTodos = cacheService.getTodos(criteria.toString());
        if (cashTodos != null) {
            return cashTodos;
        }
        Thread.sleep(3000);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        Page<Todo> page = repository.findAllByCriteria(SecurityUtils.getCurrentUser().getId(), criteria.getIsComplete(), criteria.getSearch(), pageable);
        List<TodoDto> todos = page.getContent().stream()
                .map(mapper::toDto).toList();

        PageDto<List<TodoDto>> res = new PageDto<>(
                page.getTotalElements(),
                page.getTotalPages(),
                todos
        );
        cacheService.putTodos(criteria.toString(), res);
        return res;

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
//    @CacheEvict(values = "todos", key = "#id")
    @CacheEvict(value = "list", allEntries = true)
    public void delete(String id) {
        Todo todo = validator.existsAndGet(id);
        todo.setDeletedAt(LocalDateTime.now());
        todo.setDeleted(true);
        repository.save(todo);
    }
}

// todos -> aziz : [todo1]

// aziz
