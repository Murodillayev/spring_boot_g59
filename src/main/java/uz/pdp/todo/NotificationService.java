package uz.pdp.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void send(String message, String email) {
        log.info("Send message to email. {} | {}", message, email);
    }
}
