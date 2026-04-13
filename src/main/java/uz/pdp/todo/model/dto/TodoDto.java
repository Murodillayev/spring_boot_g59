package uz.pdp.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TodoDto implements BaseDto {
    private String id;
    private String title;
    private String description;
    private boolean completed;
}

