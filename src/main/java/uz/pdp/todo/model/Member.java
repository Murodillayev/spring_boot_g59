package uz.pdp.todo.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("members")
public class Member {

    @Id
    private String id; // Mongo ObjectId -> _id -> map -> id
    private String name;
    private String phone;
    private String email;
    private String chatId;

    //Settings
    private Currency from = Currency.USD; // uzs
    private Currency to = Currency.USD; // usd

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    // sozlang
    // UZS     | UZS
    // USD *   | USD
    // RUB     | RUB
    // EUR     | EUR *
}


