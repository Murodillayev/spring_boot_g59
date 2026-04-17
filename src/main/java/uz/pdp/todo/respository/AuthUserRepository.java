package uz.pdp.todo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo.model.domain.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

    Optional<AuthUser> findByUsernameAndDeletedFalse(String username);
}
