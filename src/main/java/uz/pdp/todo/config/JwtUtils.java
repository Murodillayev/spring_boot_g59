package uz.pdp.todo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import uz.pdp.todo.model.domain.AuthUser;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtils {
    private static final String secret = "bu_secret_key_uzunligi_kamida_32_byte_bolishi_shart";

    public String generateToken(AuthUser authUser, Map<String, Object> claims) {
       return Jwts.builder()
                .signWith(getSecretKey())
                .subject(authUser.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .claims(claims)
                .compact();


    }


    public Claims exractClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new BadCredentialsException("Invalid token");
        }
        return token.replace("Bearer ", "");
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
