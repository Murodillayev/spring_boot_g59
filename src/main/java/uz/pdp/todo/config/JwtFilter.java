package uz.pdp.todo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.weaver.Utils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.todo.service.AuthUserService;
import uz.pdp.todo.utils.Constants;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;


    private final AuthUserService authUserService;

    public JwtFilter(JwtUtils jwtUtils, AuthUserService authUserService) {
        this.jwtUtils = jwtUtils;
        this.authUserService = authUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (!isPublic(request.getServletPath())) {
            // get token from header
            String authorizationData = request.getHeader("Authorization"); // Basic base64data | Bearer jwt_token

            // validate token
            String token = jwtUtils.validateToken(authorizationData);

            // decode token
            Claims claims = jwtUtils.exractClaims(token);

            // get subject
            String username = claims.getSubject();

            // load user from db
            UserDetails userDetails = authUserService.findByUsername(username);

            // create authentification(user details)
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // put to SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // do filter
        filterChain.doFilter(request, response);
    }

    private boolean isPublic(String url) {
        return Arrays.asList(Constants.WHITE_LIST)
                .contains(url);
    }


}
