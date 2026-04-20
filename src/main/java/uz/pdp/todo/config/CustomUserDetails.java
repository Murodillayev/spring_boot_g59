package uz.pdp.todo.config;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.todo.model.enums.AuthRole;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomUserDetails implements UserDetails {
    private String id;
    private String username;
    private String password;
    private AuthRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            return Collections.emptyList();
        }
        return List.of(
                new SimpleGrantedAuthority("ROLE" + role.name())
        );
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}


// filterda login apidan tashqari barcha yopiq apilarda user Details yasash uchun dbga call qilinyapti. Bu muammoni hali qiling
// User details yasashni har safar db dan load qilishni yaxshi va yomon tominini organish
// User details yasashni token orqali yasab contextholderga solishni yaxshi va yomon tominini organish