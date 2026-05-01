package uz.pdp.todo;

import org.springframework.data.mongodb.repository.MongoRepository;
import uz.pdp.todo.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByChatId(String chatId);
}
