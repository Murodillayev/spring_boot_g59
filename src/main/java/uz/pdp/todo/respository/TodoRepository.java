package uz.pdp.todo.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.todo.criteria.TodoCriteria;
import uz.pdp.todo.model.domain.Todo;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.model.dto.TodoProjection;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, String> {


    @Query(value = """
            from Todo t where not t.deleted order by t.createdAt desc
            """)
    List<Todo> findAll();


    List<Todo> findAllByDeletedFalseOrderByCreatedAtDesc();

    Optional<Todo> findAllByIdAndDeletedFalse(String id);

    List<Todo> findAllByCompletedAndCreatedAtBetweenAndDeletedFalseOrderByCreatedAtDesc(Boolean completed, Date start, Date end);

    @Query("""
            from Todo t where not t.deleted
                        and t.createdBy = :userId
                        and (:completed is null or t.completed = :completed)
                        and (t.title ilike ('%' || :search || '%') or t.description ilike ('%' || :search || '%'))
            order by t.createdAt desc
            """)
    Page<Todo> findAllByCriteria(String userId, Boolean completed, String search, Pageable pageable);

    @Query(value = """
            select new uz.pdp.todo.model.dto.TodoDto(t.id,t.title,t.description,t.completed) 
                        from Todo t 
                        where not t.deleted order by t.createdAt desc
            """)
    List<TodoDto> findAllDto();

    @Query(value = """
            select t.id as id, t.title as title, t.description as description, t.completed as completed
                        from Todo t 
                        where not t.deleted 
                                    order by t.createdAt desc
            """)
    List<TodoProjection> findAllDtoInterface();
}
