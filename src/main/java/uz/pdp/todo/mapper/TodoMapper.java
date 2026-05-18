package uz.pdp.todo.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.model.dto.TodoDto;

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
        if (todo.getId() == null) {
            throw new RuntimeException("Id is null");
        }
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .completed(todo.isCompleted())
                .description(todo.getDescription())
                .build();
    }
}
