package uz.pdp.todo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.pdp.todo.mapper.TodoMapper;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.dto.TodoCreateDto;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.repo.TodoRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository repository;

    @Mock
    private TodoMapper mapper;

    @InjectMocks
    private TodoService service;

//    @BeforeEach
//    void setUp() {
//        repository = Mockito.mock(TodoRepository.class);
//        mapper = Mockito.mock(TodoMapper.class);
//        service = new TodoService(mapper, repository);
//    }

    @Test
    void testCreatePositive() {

        TodoCreateDto dto = TodoCreateDto.builder()
                .title("some title")
                .description("some description")
                .build();


        Todo todo = new Todo();
        todo.setTitle("title");
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


        when(mapper.fromDto(dto)).thenReturn(todo);
        when(mapper.toDto(todo)).thenReturn(expect);
        when(repository.save(todo)).thenReturn(todo);

        TodoDto todoDto = service.create(dto);

        assertEquals(todoDto.getTitle(), expect.getTitle());
        assertEquals(todoDto.getDescription(), expect.getDescription());

        verify(mapper, times(1)).fromDto(dto);
        verify(mapper, times(1)).toDto(todo);
        verify(repository, atLeast(1)).save(todo);

    }
}