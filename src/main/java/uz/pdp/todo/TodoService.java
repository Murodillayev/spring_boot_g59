package uz.pdp.todo;

import org.springframework.stereotype.Service;
import uz.pdp.todo.dto.TodoCreateDto;
import uz.pdp.todo.dto.TodoDto;
import uz.pdp.todo.mapper.TodoMapper;

import java.util.List;

@Service
public class TodoService {

    private final TodoMapper mapper;
    private final TodoRepository repository;

    public TodoService(TodoMapper mapper, TodoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public TodoDto create(TodoCreateDto dto) {
        dto.validate();
        Todo todo = mapper.fromDto(dto);
        return mapper.toDto(repository.save(todo));
    }

    public List<TodoDto> getAll() {
        List<Todo> todos = repository.findAll();
        return mapper.toDto(todos);
    }
}
