package uz.pdp.todo.criteria;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TodoCriteria extends BaseCriteria {
    private LocalDate fromDate;
    private LocalDate toDate;
}
