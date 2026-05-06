package uz.pdp.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@Service
public class AuthUserService {
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthUserService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public AuthUser register(AuthUser user) {

        // create user logic (validate, mapping, save)
        log.info("Register user: {}", user);
        applicationEventPublisher.publishEvent(new AuthUserCreateEvent(this, user));

        return user;

        //
    }
}
