package uz.pdp.todo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class MVRefresher {

    public void refreshUsersStatistic() {

        if (new Random().nextBoolean()) {
            throw new RuntimeException("Not implemented");
        }
        log.info("Refresh users statistic mv");
    }
}
