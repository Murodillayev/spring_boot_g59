package uz.pdp.todo;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.service.TodoService;

@ShellComponent
public class TodoCommands {
    private final TodoService todoService;
    private final SecurityContext securityContext;

    public TodoCommands(TodoService todoService, SecurityContext securityContext) {
        this.todoService = todoService;
        this.securityContext = securityContext;
    }

    @ShellMethod(value = "Salom beradi")
    public String hello(
            @ShellOption String name,
            @ShellOption(value = "l") String lastName
    ) {
        return "Salom " + name + " " + lastName;
    }

    @ShellMethod(value = "Todo yaratish")
    public String create(
            @ShellOption(value = "-t") String title,
            @ShellOption(value = "-d") String description
    ) {

        todoService.create(TodoCreateDto.builder()
                .description(description)
                .title(title)
                .build());
        return "Created " + title + ": " + description;
    }

    @ShellMethod(value = "Login qilish")
    public String login(
            @ShellOption(value = "-u") String username,
            @ShellOption(value = "-p") String password
    ) {
        if (username.equals("admin") && password.equals("123")) {
            securityContext.setLoggedIn(true);
            return "Successfully logged in";
        }
        return "Bad credentials";
    }

    @ShellMethod(value = "logout qilish")
    public String logout() {
        securityContext.setLoggedIn(false);
        return "Bye!!";
    }


    @ShellMethodAvailability(value = {"create","update","getAll"})
    public Availability isAuthorized() {
        if (securityContext.isLoggedIn()) {
            return Availability.available();
        } else {
            return Availability.unavailable("You are not logged in");
        }
    }


}
