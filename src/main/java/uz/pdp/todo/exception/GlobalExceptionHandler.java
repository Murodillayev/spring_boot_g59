package uz.pdp.todo.exception;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.todo.dto.AppErrorDto;

import java.util.Arrays;
import java.util.List;

// Bean validation
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<AppErrorDto> handleException(BadRequestException ex, HttpServletRequest request) {

        AppErrorDto errorDto = AppErrorDto.builder()
                .message(ex.getMessage())
                .developerMessage(Arrays.toString(ex.getStackTrace()))
                .path(request.getServletPath())
                .build();

//        return new ResponseEntity<>(
//                errorDto,
//                HttpStatus.BAD_REQUEST
//        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler({PermissionDenidedException.class})
    public ResponseEntity<AppErrorDto> handleException(PermissionDenidedException ex, HttpServletRequest request) {

        AppErrorDto errorDto = AppErrorDto.builder()
                .message(ex.getMessage())
                .developerMessage(Arrays.toString(ex.getStackTrace()))
                .path(request.getServletPath())
                .build();

        return new ResponseEntity<>(
                errorDto,
                HttpStatus.BAD_REQUEST
        );
    }


}
