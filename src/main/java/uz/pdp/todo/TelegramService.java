package uz.pdp.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TelegramService {
    public void sendMessageToModerator(String message) {
        log.info("Send message to moderator | message = {}", message);
    }
}
