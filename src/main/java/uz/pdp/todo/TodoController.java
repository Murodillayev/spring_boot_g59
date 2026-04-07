package uz.pdp.todo;

import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.dto.TodoCreateDto;
import uz.pdp.todo.dto.TodoDto;
import uz.pdp.todo.dto.TodoUpdateDto;

import java.util.List;

@RequestMapping("/todo")
@RestController
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
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

