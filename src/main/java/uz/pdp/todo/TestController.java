package uz.pdp.todo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo.dto.AppErrorDto;
import uz.pdp.todo.dto.RegisterDto;
import uz.pdp.todo.dto.UserDto;
import uz.pdp.todo.exception.BadRequestException;

import java.util.Arrays;

@RestController
public class TestController {

    private final UserService service;

    public TestController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterDto dto, HttpServletRequest request) {

//        try {
        return ResponseEntity
                .status(200)
                .body(service.register(dto));

//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(AppErrorDto.builder()
//                            .path(request.getRequestURI())
//                            .message(e.getMessage())
//                            .developerMessage(Arrays.toString(e.getStackTrace()))
//                            .build());
//        }
    }

//    @ExceptionHandler({BadRequestException.class})
//    public ResponseEntity<AppErrorDto> handleException(BadRequestException ex, HttpServletRequest request) {
//
//        AppErrorDto errorDto = AppErrorDto.builder()
//                .message(ex.getMessage())
//                .developerMessage(Arrays.toString(ex.getStackTrace()))
//                .path(request.getServletPath())
//                .build();
//
//        return new ResponseEntity<>(
//                errorDto,
//                HttpStatus.BAD_REQUEST
//        );
//    }
}
