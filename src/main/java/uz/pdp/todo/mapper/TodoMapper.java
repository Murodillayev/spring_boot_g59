package uz.pdp.todo.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.todo.Todo;
import uz.pdp.todo.dto.TodoCreateDto;
import uz.pdp.todo.dto.TodoDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TodoMapper {
    public Todo fromDto(TodoCreateDto dto) {
        Todo todo = new Todo();
        todo.setId(UUID.randomUUID().toString());
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        return todo;
    }

    public TodoDto toDto(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .completed(todo.isCompleted())
                .description(todo.getDescription())
                .build();
    }
}
