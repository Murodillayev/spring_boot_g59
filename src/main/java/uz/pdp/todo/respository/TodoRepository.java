package uz.pdp.todo.respository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.todo.model.domain.Todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo save(Todo todo) {
        Optional<Todo> byId = findById(todo.getId());
        if (byId.isPresent()) {
            jdbcTemplate.update("""
                     update todo
                      set title = ?,description =?,completed = ?, deleted = ?, updated_at = ?
                      where id = ?
                    """, todo.getTitle(), todo.getDescription(), todo.isCompleted(), todo.isDeleted(), LocalDateTime.now(), todo.getId());
        } else {
            jdbcTemplate.update("""
                       insert into todo ( id,title, description) 
                                            values (?, ?, ?)
                    """, todo.getId(), todo.getTitle(), todo.getDescription());
        }

        return findById(todo.getId()).get();
    }

    private Optional<Todo> findById(String id) {
        String sql = """
                select a.*
                from todo a
                where a.id = ?""";

        try {
            Todo todo = jdbcTemplate.queryForObject(sql, getRowMapper(), id);
            return Optional.of(todo);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public RowMapper<Todo> getRowMapper() {
        return (rs, rowNum) -> {
            Todo author = new Todo();
            author.setId(rs.getString("id"));
            author.setDescription(rs.getString("description"));
            author.setTitle(rs.getString("title"));
            author.setDeleted(rs.getBoolean("deleted"));
            author.setCompleted(rs.getBoolean("completed"));
            return author;
        };
    }

    public List<Todo> findAll() {
        String sql = """
                        select * from todo where not deleted order by created_at desc
                """;
        return jdbcTemplate.query(sql, getRowMapper());
    }
}
