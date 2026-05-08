package uz.pdp.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.todo.model.domain.AuthUser;
import uz.pdp.todo.model.enums.AuthRole;
import uz.pdp.todo.respository.AuthUserRepository;

@SpringBootApplication
@EnableCaching
public class TodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }


//    @Bean
    public CommandLineRunner init(PasswordEncoder encoder, AuthUserRepository repository) {
        return args -> {
            AuthUser authUser = new AuthUser();
            authUser.setUsername("admin");
            authUser.setPassword(encoder.encode("123"));
            authUser.setEmail("muhammadkomil@gmail.com");
            authUser.setFullName("Muhammadkomil Murodillayev");
            authUser.setRole(AuthRole.ADMIN);
            repository.save(authUser);
        };
    }
}
