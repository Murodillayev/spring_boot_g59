package uz.pdp.todo.model.dto;


import lombok.Data;

@Data
public class TodoUpdateDto {
    private String title;
    private String description;
}
