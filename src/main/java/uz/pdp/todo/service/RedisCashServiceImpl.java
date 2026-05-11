package uz.pdp.todo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import uz.pdp.todo.model.dto.PageDto;
import uz.pdp.todo.model.dto.TodoDto;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Profile("redis")
public class RedisCashServiceImpl implements CacheService {

    private final ValueOperations<String, PageDto<List<TodoDto>>> operations;

    public RedisCashServiceImpl(RedisTemplate<String, PageDto<List<TodoDto>>> redisTemplate) {
        this.operations = redisTemplate.opsForValue();
    }

    @Override
    public PageDto<List<TodoDto>> getTodos(String key) {
        return operations.get(key);
    }

    @Override
    public void putTodos(String key, PageDto<List<TodoDto>> page) {
        operations.set(key, page, 10, TimeUnit.SECONDS);
    }
}
