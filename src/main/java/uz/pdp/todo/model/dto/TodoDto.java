package uz.pdp.todo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TodoDto {
    private String id;
    private String title;
    private String description;
    private boolean completed;
}

