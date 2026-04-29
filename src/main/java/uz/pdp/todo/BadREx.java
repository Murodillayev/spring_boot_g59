package uz.pdp.todo;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

@Getter
public class BadREx extends RuntimeException {
    private final HttpStatus status;

    public BadREx(String mes, HttpStatus status) {
        super(mes);
        this.status = status;
    }
}
