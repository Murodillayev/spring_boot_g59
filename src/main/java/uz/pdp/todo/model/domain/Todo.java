package uz.pdp.todo.model.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo extends BaseEntity {
    private String title;
    private String description;
    private boolean completed;
}
