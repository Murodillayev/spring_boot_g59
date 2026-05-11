package uz.pdp.todo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import uz.pdp.todo.model.dto.PageDto;
import uz.pdp.todo.model.dto.TodoDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Profile("!redis")
public class CacheServiceImpl implements CacheService {

    private final Map<String, PageDto<List<TodoDto>>> TODOS = new ConcurrentHashMap<>();

    @Override
    public PageDto<List<TodoDto>> getTodos(String key) {
        return TODOS.get(key);
    }

    @Override
    public void putTodos(String key, PageDto<List<TodoDto>> page) {
        TODOS.put(key, page);
    }
}
