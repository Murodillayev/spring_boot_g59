package uz.pdp.todo.handler;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import uz.pdp.todo.service.TelegramService;
import uz.pdp.todo.annotations.Handler;

@Handler
public class CallbackQueryHandler {
    private final TelegramService telegramService;

    public CallbackQueryHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    public void handle(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        String data = callbackQuery.getData();
        Integer messageId = callbackQuery.getMessage().getMessageId();

        if (data.startsWith("from")) {
            String from = data.replace("from_", "").toUpperCase();
            telegramService.updateFrom(chatId, from, messageId);


        } else if (data.startsWith("to")) {
            String to = data.replace("to_", "").toUpperCase();
            telegramService.updateTo(chatId, to, messageId);
        }

    }
}
