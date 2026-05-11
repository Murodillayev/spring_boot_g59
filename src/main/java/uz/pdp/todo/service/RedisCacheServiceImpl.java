package uz.pdp.todo.service;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheServiceImpl implements CacheService {

    private final ValueOperations<String, Long> CODES;

    public RedisCacheServiceImpl(RedisTemplate<String, Long> redisTemplate) {
        CODES = redisTemplate.opsForValue();
    }


    @Override
    public void putConfirmCode(String code, Long value) {
        CODES.set("CONFIRM_CODE:" + code, value, 20, TimeUnit.SECONDS);
    }
    //CONFIRM_CODE:

    @Override
    public Long checkCode(String code) {
        return CODES.get("CONFIRM_CODE:" + code);
    }



}
