package uz.pdp.todo;


import org.springframework.stereotype.Component;

@Component
public class SecurityContext {

    private boolean loggedIn = false;

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
