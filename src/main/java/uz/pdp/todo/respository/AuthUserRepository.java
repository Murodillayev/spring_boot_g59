package uz.pdp.todo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo.model.domain.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
}
