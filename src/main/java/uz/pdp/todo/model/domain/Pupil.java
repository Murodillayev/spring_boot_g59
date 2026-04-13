package uz.pdp.todo.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Pupil extends BaseEntity {
    private String fullName;
    private LocalDate birthDate;

}
