package uz.pdp.todo.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.pdp.todo.service.TelegramService;

@Component
public class MessageHandler {
    private final TelegramService telegramService;

    public MessageHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    public void handle(Message message) {
        String chatId = message.getChatId().toString();
        String text = message.getText();


        if ("/start".equals(text)) {
            telegramService.sendWelcome(message);

        } else if (text != null && text.matches("\\d+")) {
            telegramService.sendConvertResult(text, chatId);

        } else if ("/settings".equals(text)) {
            telegramService.sendSettings(chatId);

        } else {
            telegramService.deleteMessage(message.getMessageId(), chatId);
        }

    }

}
