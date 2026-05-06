package uz.pdp.todo;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AuthUserCreateEvent extends ApplicationEvent {

    private final AuthUser authUser;

    public AuthUserCreateEvent(Object source, AuthUser authUser) {
        super(source);
        this.authUser = authUser;
    }
}
