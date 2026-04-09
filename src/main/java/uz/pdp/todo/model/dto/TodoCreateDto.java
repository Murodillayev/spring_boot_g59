package uz.pdp.todo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateDto implements BaseDto {
    private String title;
    private String description;
}
