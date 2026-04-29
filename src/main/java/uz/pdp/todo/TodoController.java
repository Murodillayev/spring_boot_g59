package uz.pdp.todo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.pdp.todo.dto.TodoCreateDto;

import java.rmi.MarshalledObject;

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
    public String create(@ModelAttribute TodoCreateDto dto, Model model) {
        service.create(dto);
        return "redirect:/";
    }
}
