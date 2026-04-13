package uz.pdp.todo.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseCriteria {
    private String search = "";
    private Integer page = 0;
    private Integer size = 10;

}
