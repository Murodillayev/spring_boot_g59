package uz.pdp.todo.model.domain;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Todo extends BaseEntity {
    private String title;
    private String description;
    private boolean completed;
}

// interface based
// class based
