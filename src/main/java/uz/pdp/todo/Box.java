package uz.pdp.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Box<T> {
    public T value;
    private LocalDateTime expired;
}
