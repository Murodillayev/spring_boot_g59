package uz.pdp.todo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class AuthUserListener {

    private final NotificationService notificationService;
    private final TelegramService telegramService;
    private final MVRefresher mVRefresher;

    public AuthUserListener(NotificationService notificationService, TelegramService telegramService, MVRefresher mVRefresher) {
        this.notificationService = notificationService;
        this.telegramService = telegramService;
        this.mVRefresher = mVRefresher;
    }

    @EventListener(AuthUserCreateEvent.class)
    public void sendNotify(AuthUserCreateEvent event) {
        // send message to user email
        notificationService.send("Successfully registered", event.getAuthUser().getEmail());
    }

    @EventListener(AuthUserCreateEvent.class)
    public void sendModerator(AuthUserCreateEvent event) {
        // send message to modetator
        telegramService.sendMessageToModerator("User registered. User=" + event.getAuthUser());

    }

    @TransactionalEventListener(value = AuthUserCreateEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void refreshMv(AuthUserCreateEvent event) {
        // send message to modetator
        mVRefresher.refreshUsersStatistic();
    }


    @TransactionalEventListener(classes = {AuthUserCreateEvent.class}, phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(AuthUserCreateEvent event) {
        // send message to modetator
        log.info("Xatolik yuz berdi. Xatolik haqida adminga xabar yuborildi");
    }



    // toliq mvc (spring mvc)
    // rest(sprinh) + react
    // rest + spring mvc
}
