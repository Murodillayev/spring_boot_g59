package uz.pdp.todo.validator;


import org.springframework.stereotype.Component;
import uz.pdp.todo.model.domain.Todo;
import uz.pdp.todo.respository.TodoRepository;

@Component
public class TodoValidator {
    private final TodoRepository todoRepository;

    public TodoValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo existsAndGet(String id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return todoRepository.findAllByIdAndDeletedFalse(id).orElseThrow(
                () -> new RuntimeException("Todo not found")
        );
    }
}
