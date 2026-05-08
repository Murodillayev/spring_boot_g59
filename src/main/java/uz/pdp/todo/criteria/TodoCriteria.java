package uz.pdp.todo.criteria;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class TodoCriteria extends BaseCriteria {
    private LocalDate fromDate;
    private LocalDate toDate;
    private Boolean isComplete;

    @Builder
    public TodoCriteria(String search, Integer page, Integer size, LocalDate fromDate, LocalDate toDate, Boolean isComplete) {
        super(search, page, size);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isComplete = isComplete;
    }
}
