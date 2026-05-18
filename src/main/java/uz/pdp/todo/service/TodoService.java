package uz.pdp.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.repo.TodoRepository;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.model.dto.TodoUpdateDto;
import uz.pdp.todo.mapper.TodoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoMapper mapper;
    private final TodoRepository repository;


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
