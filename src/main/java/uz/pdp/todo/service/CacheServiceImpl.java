package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.Box;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheServiceImpl implements CacheService {

    private final Map<String, Box<Long>> CODES = new ConcurrentHashMap<>();

    @Override
    public void putConfirmCode(String code, Long value) {
        Box<Long> box = new Box<>(value, LocalDateTime.now().plusSeconds(20));
        CODES.put(code, box);
    }

    @Override
    public Long checkCode(String code) {
        Box<Long> box = CODES.get(code);
        if (box == null || box.getExpired().isBefore(LocalDateTime.now())) {
            return null;
        }
        return box.getValue();
    }
}
