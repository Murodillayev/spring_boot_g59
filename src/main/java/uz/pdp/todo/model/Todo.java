package uz.pdp.todo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Todo {

    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
