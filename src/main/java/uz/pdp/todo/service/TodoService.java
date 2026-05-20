package uz.pdp.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.todo.mapper.TodoMapper;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.model.dto.TodoUpdateDto;
import uz.pdp.todo.repo.TodoRepository;

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
        Todo todo = mapper.fromDto(dto);
        return mapper.toDto(repository.save(todo));
    }


    public List<TodoDto> getAll() {
        List<Todo> todos = repository.findAllByDeletedFalse();
        return todos.stream().map(mapper::toDto).toList();
    }

    public TodoDto update(String id, TodoUpdateDto dto) {
        Todo todo = repository.findById(id).orElse(null);
        if (todo == null) {
            throw new RuntimeException("Todo not found");
        }
        mapper.fromDto(dto, todo);
        return mapper.toDto(repository.save(todo));
    }

    @Transactional
    public void delete(String id) {
        Todo todo = repository.findById(id).orElse(null);
        if (todo == null) {
            return;
        }
        todo.setDeleted(true);

    }

    @Transactional
    public void completed(String id) {
        Todo todo = repository.findById(id).orElse(null);
        if (todo == null) {
            throw new RuntimeException("Todo not found");
        }
        todo.setCompleted(true);
//        repository.save(todo);
    }
}
