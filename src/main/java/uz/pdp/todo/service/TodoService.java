package uz.pdp.todo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.todo.criteria.BaseCriteria;
import uz.pdp.todo.criteria.TodoCriteria;
import uz.pdp.todo.respository.TodoRepository;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.model.dto.TodoUpdateDto;
import uz.pdp.todo.mapper.TodoMapper;
import uz.pdp.todo.model.domain.Todo;
import uz.pdp.todo.validator.TodoValidator;

import java.util.List;

@Service
public class TodoService
        extends AbstractService<TodoRepository, TodoMapper, TodoValidator>
        implements CRUDService<TodoCreateDto, TodoDto, TodoUpdateDto, String, TodoCriteria> {


    public TodoService(TodoRepository repository, TodoMapper mapper, TodoValidator validator) {
        super(repository, mapper, validator);
    }

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
    public List<TodoDto> getAll(TodoCriteria criteria) {
        List<Todo> todos = repository.findAll();
        return todos.stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(String id) {

    }
}
