package uz.pdp.todo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.todo.config.CustomUserDetails;
import uz.pdp.todo.model.domain.AuthUser;
import uz.pdp.todo.respository.AuthUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // load from db by username
        AuthUser authUser = authUserRepository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> new UsernameNotFoundException(username));
        // return user details
        return CustomUserDetails.builder()
                .id(authUser.getId())
                .username(authUser.getUsername())
                .password(authUser.getPassword())
                .role(authUser.getRole())
                .build();
    }
}
