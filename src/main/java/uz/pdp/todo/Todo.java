package uz.pdp.todo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Todo {

    @Id
    private String id = UUID.randomUUID().toString();
    private String title;
    private String description;
    private boolean completed;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
