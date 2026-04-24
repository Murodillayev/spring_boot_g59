package uz.pdp.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

    private String id;
    private String title;
    private String userId;

    @JsonProperty("body")
    private String description;
}
