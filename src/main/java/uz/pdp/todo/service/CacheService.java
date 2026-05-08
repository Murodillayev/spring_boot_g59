package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.model.dto.TodoDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CacheService {

    private final Map<String, TodoDto> cache = new HashMap<>();

    public void put(String key, TodoDto todoDto) {
        cache.put(key, todoDto);
    }

    public TodoDto get(String key) {
        try {
            return cache.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public void remove(String id) {
        cache.remove(id);
    }
}
