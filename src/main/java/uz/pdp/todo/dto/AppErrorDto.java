package uz.pdp.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AppErrorDto {
    private String message;
    private String developerMessage;
    private String path;
}
