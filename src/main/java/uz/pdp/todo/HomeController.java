package uz.pdp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.todo.dto.TodoDto;

import java.util.List;

@Controller
public class HomeController {
    private final TodoService todoService;

    public HomeController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String indexPage(Model model) {
        List<TodoDto> todos = todoService.getAll();
        model.addAttribute("todos", todos);
        return "index";
    }
}
