package uz.pdp.todo.model.domain;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Teacher extends BaseEntity {
    private String firstName;
    private String lastName;
    private String specialty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_pupil",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "pupil_id")
    )
    private List<Pupil> pupils;
}


// Teacher (..., List.of(new Puli,p,p))