package uz.pdp.todo.controller;

import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import uz.pdp.todo.service.TodoService;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.model.dto.TodoUpdateDto;

import java.util.List;

@RequestMapping("/todo")
@RestController
public class TodoController {

    private final ObjectMapper objectMapper;
    private final TodoService service;

    public TodoController(ObjectMapper objectMapper, TodoService service) {
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @PostMapping
    public TodoDto create(@RequestBody TodoCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<TodoDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TodoDto get(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {

        return "Sucessfully deleted!";
    }


    @PutMapping("/{id}")
    @ResponseBody
    public TodoDto update(@PathVariable String id, @RequestBody TodoUpdateDto dto) {
        return service.update(id, dto);
    }


}

