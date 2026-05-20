package uz.pdp.todo;

import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.service.TodoService;

import java.util.List;

@Component
public class TodoIdValueProvider implements ValueProvider {
    private final TodoService todoService;

    public TodoIdValueProvider(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public List<CompletionProposal> complete(CompletionContext completionContext) {
        String currentWord = completionContext.currentWord();
        String prefix = (currentWord == null) ? "" : currentWord;

        return todoService.getAll().stream()
                .filter(t -> t.getId().startsWith(prefix))
                .map(t -> new CompletionProposal(t.getId()))
                .toList();
    }
}
