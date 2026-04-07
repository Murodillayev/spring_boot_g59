package uz.pdp.todo;

import org.springframework.stereotype.Service;
import uz.pdp.todo.dto.TodoCreateDto;
import uz.pdp.todo.dto.TodoDto;
import uz.pdp.todo.dto.TodoUpdateDto;
import uz.pdp.todo.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoMapper mapper;
    private final TodoRepository repository;

    public TodoService(TodoMapper mapper, TodoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public TodoDto create(TodoCreateDto dto) {
        Todo todo = mapper.fromDto(dto);
        return mapper.toDto(repository.save(todo));
    }

    public List<TodoDto> getAll() {
        List<Todo> todos = repository.findAll();
        return todos.stream().map(mapper::toDto).toList();
    }

    public TodoDto update(String id, TodoUpdateDto dto) {
        return null;
    }
}
