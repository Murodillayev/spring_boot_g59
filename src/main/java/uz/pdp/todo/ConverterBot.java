package uz.pdp.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.todo.config.AppConfig;
import uz.pdp.todo.handler.CallbackQueryHandler;
import uz.pdp.todo.handler.MessageHandler;

@Slf4j
@Component
public class ConverterBot extends TelegramLongPollingBot {

    private final AppConfig config;
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;

    public ConverterBot(AppConfig config, MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler) {
        super(config.getToken());
        this.config = config;
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            messageHandler.handle(update.getMessage());

        } else if (update.hasCallbackQuery()) {
            callbackQueryHandler.handle(update.getCallbackQuery());

        }
    }

    @Override
    public String getBotUsername() {
        return config.getUsername();
    }

    public int sendMessage(SendMessage sendMessage) {
        try {
            Integer messageId = execute(sendMessage).getMessageId();
            log.info("Sending message id {} to message id {}", messageId, messageId);
            return messageId;
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
            return -1;
        }

    }

    public void deleteMessage(DeleteMessage deleteMessage) {
        try {
            execute(deleteMessage);
            log.debug("Delete message: {}", deleteMessage.getMessageId());
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void editMessage(EditMessageText editMessageText) {

        try {
            execute(editMessageText);
            log.info("Edit message: {}", editMessageText.getMessageId());
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }
}
