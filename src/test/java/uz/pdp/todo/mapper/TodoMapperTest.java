package uz.pdp.todo.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.dto.TodoDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoMapperTest {

    @InjectMocks
    private TodoMapper mapper;

    @Test
    void testFromDtoPositive() {
        Todo todo = new Todo();
        todo.setTitle("some title");
        todo.setCreatedAt(LocalDateTime.now());
        todo.setDescription("some description");
        todo.setCompleted(false);
        todo.setId("1");
        todo.setUpdatedAt(LocalDateTime.now());
        todo.setDeleted(false);

        TodoDto expect = TodoDto.builder()
                .id("1")
                .title("some title")
                .description("some description")
                .completed(false)
                .build();

        TodoDto result = mapper.toDto(todo);

        assertEquals(expect.getId(), result.getId());
        assertEquals(expect.getTitle(), result.getTitle());
        assertEquals(expect.getDescription(), result.getDescription());
        assertEquals(expect.isCompleted(), result.isCompleted());


    }

    @Test
    void testFromDtoNegative() {
        Todo todo = new Todo();
        todo.setId(null);
        todo.setTitle("some title");
        todo.setCreatedAt(LocalDateTime.now());
        todo.setDescription("some description");
        todo.setCompleted(false);
        todo.setUpdatedAt(LocalDateTime.now());
        todo.setDeleted(false);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> mapper.toDto(todo));
        assertEquals("Id is null", runtimeException.getMessage());


    }
}