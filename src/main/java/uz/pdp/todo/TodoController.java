package uz.pdp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.todo.dto.TodoCreateDto;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createPage() {
        return "todo/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute TodoCreateDto dto) {
        service.create(dto);
        return "todo/list";
    }
}
