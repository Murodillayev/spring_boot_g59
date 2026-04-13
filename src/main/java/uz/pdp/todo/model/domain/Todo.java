package uz.pdp.todo.model.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Todo extends BaseEntity {
    private String title;
    private String description;
    private boolean completed;

    @ManyToOne
//    @JoinColumn(name = "user_id")
    private AuthUser user;
}

// interface based
// class based
