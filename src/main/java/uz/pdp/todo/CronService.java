package uz.pdp.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CronService {

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public static void checkSubscription() {
        log.info("Checking subscription");
    }

    @Scheduled(cron = "${application.refresh-mv-expression:*/2 * * * * *}")
    public static void refreshMv() {
        log.info("Refreshing mv");
    }


//    public static void main(String[] args) {
//
//        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//
//        scheduledExecutorService.scheduleAtFixedRate(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        checkSubscription();
//                    }
//                },
//                0,
//                1,
//                TimeUnit.SECONDS
//        );
//
//    }
}
