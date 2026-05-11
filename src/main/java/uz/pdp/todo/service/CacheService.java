package uz.pdp.todo.service;

import uz.pdp.todo.model.dto.PageDto;
import uz.pdp.todo.model.dto.TodoDto;

import java.util.List;

public interface CacheService {

   PageDto<List<TodoDto>> getTodos(String key);

   void putTodos(String key, PageDto<List<TodoDto>> page);
}
