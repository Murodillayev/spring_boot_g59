package uz.pdp.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import uz.pdp.todo.config.CustomUserDetails;
import uz.pdp.todo.criteria.TodoCriteria;
import uz.pdp.todo.model.dto.*;
import uz.pdp.todo.service.TodoService;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/todo")
@RestController
public class TodoController {

    private final ObjectMapper objectMapper;
    private final TodoService service;

    public TodoController(ObjectMapper objectMapper, TodoService service) {
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @PostMapping
    public TodoDto create(@RequestBody TodoCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping("/projection-test")
    @Operation(summary = "Bu api projectnni test qilish uchun edi. Bu prodda ishlatilamydi",description = "Bu descriptiion")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Foydalanuvchi muvaffaqiyatli topildi"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Bunday ID li foydalanuvchi mavjud emas"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AppErrorDto.class) // Mana shu yerda DTO ni ulaymiz
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Serverda kutilmagan xatolik yuz berdi"
            )
    })
    @Deprecated
    public List<TodoProjection> getAll() {
        return service.getAllDtoInterface();
    }

    @GetMapping
    public PageDto<List<TodoDto>> getAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) LocalDate fromCreate,
            @RequestParam(required = false) LocalDate toCreate,
            @RequestParam(required = false) Boolean completed,
            @AuthenticationPrincipal CustomUserDetails currentUser
            ) throws InterruptedException {
        return service.getAll(


                TodoCriteria.builder()
                        .fromDate(fromCreate)
                        .toDate(toCreate)
                        .isComplete(completed)
                        .page(page)
                        .size(size)
                        .search(search)
                        .build()
        );

//        return service.getAllDto();
    }

    @GetMapping("/{id}")
    public TodoDto get(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "Sucessfully deleted!";
    }


    @PutMapping("/{id}")
    public TodoDto update(@PathVariable String id, @RequestBody TodoUpdateDto dto) {
        return service.update(dto, id);
    }


}

