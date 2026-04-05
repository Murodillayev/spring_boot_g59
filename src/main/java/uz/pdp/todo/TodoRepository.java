package uz.pdp.todo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

        return byId.get();
    }

    private Optional<Todo> findById(String id) {
        String sql = """
                select a.*
                from todo a
                where not a.id
                  and a.id = ?""";

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
}
