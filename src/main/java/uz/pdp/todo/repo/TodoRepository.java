package uz.pdp.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo.model.Todo;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, String> {
    List<Todo> findAllByDeletedFalse();
}
