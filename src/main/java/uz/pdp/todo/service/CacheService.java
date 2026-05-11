package uz.pdp.todo.service;

public interface CacheService {
    void putConfirmCode(String code, Long value);

    Long checkCode(String code);
}
