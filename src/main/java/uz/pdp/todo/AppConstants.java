package uz.pdp.todo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConstants {

    @Value("${application.remotes.json-place:https://jsonplaceholder.typicode.com/posts}")
    private String jsonPlaceHolderApi;
}
