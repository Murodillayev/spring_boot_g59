package uz.pdp.todo.mapper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.todo.config.CustomUserDetails;
import uz.pdp.todo.config.SecurityUtils;
import uz.pdp.todo.model.domain.Todo;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.model.dto.TodoUpdateDto;

import java.util.UUID;

@Component
public class TodoMapper {
    public Todo fromDto(TodoCreateDto dto) {
//        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Todo todo = new Todo();
        todo.setId(UUID.randomUUID().toString());
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCreatedBy(SecurityUtils.getCurrentUser().getId());
        return todo;
    }

    public void fromDto(Todo todo, TodoUpdateDto dto) {
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setUpdatedBy(SecurityUtils.getCurrentUser().getId());
    }

    public TodoDto toDto(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .completed(todo.isCompleted())
                .description(todo.getDescription())
                .build();
    }
}
