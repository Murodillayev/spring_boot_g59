package uz.pdp.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateDto {
    private String title;
    private String description;
}
