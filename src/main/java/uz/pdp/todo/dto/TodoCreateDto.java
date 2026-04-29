package uz.pdp.todo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import uz.pdp.todo.BadREx;

@Getter
@Setter
public class TodoCreateDto {
    private String title;
    private String description;


    public void validate(){
        if (title == null || title.trim().isEmpty()){
            throw new RuntimeException("Title cannot be empty");
        }
        if (title.length() < 3){
            throw new RuntimeException("Title must be at least 3 characters");
        }
        if (description == null || description.trim().isEmpty()){
            throw new RuntimeException("Description cannot be empty");
        }

        if (description.length() < 10){
            throw new BadREx("Description must be at least 10 characters", HttpStatus.REQUEST_TIMEOUT);
        }
    }
}
