package uz.pdp.todo;

import org.springframework.graphql.data.method.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.model.dto.TodoUpdateDto;
import uz.pdp.todo.service.TodoService;

import java.util.List;

@Controller

public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

//    @SchemaMapping(typeName = "Query")
    @QueryMapping
    public TodoDto get(@Argument String id) {
        return service.get(id);
    }

    @SchemaMapping(typeName = "Query", value = "findAll")
    public List<TodoDto> getAll() {
        return service.getAll();
    }

//    @SchemaMapping(typeName = "Mutation")
    @MutationMapping
    public TodoDto create(@Argument TodoCreateDto dto) {
        return service.create(dto);
    }

    @SchemaMapping(typeName = "Mutation")
    public TodoDto update(@Argument TodoUpdateDto dto, @Argument String id) {
        return service.update(id, dto);
    }

    @SchemaMapping(typeName = "Mutation")
    public Boolean delete(@Argument String id) {
        service.delete(id);
        return true;
    }
}
