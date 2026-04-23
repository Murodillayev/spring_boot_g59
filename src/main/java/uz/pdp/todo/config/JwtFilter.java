package uz.pdp.todo.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.todo.model.enums.AuthRole;
import uz.pdp.todo.service.AuthUserService;
import uz.pdp.todo.utils.Constants;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final AuthUserService authUserService;
    private final YmlData ymlData;

    public JwtFilter(JwtUtils jwtUtils, AuthUserService authUserService, YmlData ymlData) {
        this.jwtUtils = jwtUtils;
        this.authUserService = authUserService;
        this.ymlData = ymlData;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (!isPublic(request.getServletPath())) {
            // get token from header
            String authorizationData = request.getHeader("Authorization"); // Basic base64data | Bearer jwt_token

            // validate token
            Claims claims = jwtUtils.validateAccessToken(authorizationData);
            UserDetails userDetails = makeUserDetails(claims);

            // create authentification(user details)
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // put to SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // do filter
        filterChain.doFilter(request, response);
    }

    private UserDetails makeUserDetails(Claims claims) {

        String username = claims.getSubject();
        if (ymlData.getUserniDbDanOlibYasasinmi()) {
            return authUserService.findByUsername(username);
        }

        String roleName = claims.get("role", String.class);
        String userId = claims.get("user_id", String.class);
        return new CustomUserDetails(userId, username, null, AuthRole.valueOf(roleName));
    }

    private boolean isPublic(String url) {
        return Arrays.asList(Constants.WHITE_LIST)
                .contains(url);
    }


    // user comment       select * from user_comment uc join comment c on uc.id = c.rep_id

    // commnet (id,text, userid, rep_id)
    // 1 salom 1 null
    // 2 salom 2 1
    // 3 😂 4 1
    // 4 ?  1 3


    // create view u_comment (user join comment)

    // select u_comment
    // select mv_u_comment
    // select u_comment join commment

}
