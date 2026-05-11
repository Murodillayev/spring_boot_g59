package uz.pdp.todo.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class PageDto<T> implements Serializable {
    private Long allElements;
    private Integer totalPages;
    private T elements;

    public PageDto(Long allElements, Integer totalPages, T elements) {
        this.allElements = allElements;
        this.totalPages = totalPages;
        this.elements = elements;
    }
}
