package uz.pdp.todo.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private String lang = "uz";
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message,String lang) {
        super(message);
        this.lang = lang;
    }
}
