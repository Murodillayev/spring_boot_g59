package uz.pdp.todo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import uz.pdp.todo.model.domain.AuthUser;
import uz.pdp.todo.model.dto.TokenDto;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtils {
    private final Long accessTokenExp = 1000 * 20L; // 3 min
    private final Long refreshTokenExp = 1000 * 60 * 60 * 24L * 3; // 3 kun

    private static final String secret = "bu_secret_key_uzunligi_kamida_32_byte_bolishi_shart";

    public TokenDto generateAccessToken(AuthUser authUser, Map<String, Object> claims) {
        Date expiry = new Date(System.currentTimeMillis() + accessTokenExp);
        String token = buildToken(authUser.getUsername(), claims, expiry);
        return TokenDto.builder()
                .token(token)
                .expiry(expiry)
                .build();

    }

    public TokenDto generateRefreshToken(AuthUser authUser) {
        Date expiry = new Date(System.currentTimeMillis() + refreshTokenExp);

        String token = buildToken(authUser.getUsername(), Map.of(), expiry);
        return TokenDto.builder()
                .token(token)
                .expiry(expiry)
                .build();
    }

    public Claims exractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Claims validateAccessToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new BadCredentialsException("Invalid token");
        }

        Claims claims = exractClaims(token.replace("Bearer ", ""));
        if (!"access_token".equals(claims.get("type", String.class))) {
            throw new BadCredentialsException("Invalid token");
        }
        return claims;

    }



    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    private String buildToken(String username, Map<String, Object> claims, Date expiration) {
        return Jwts.builder()
                .signWith(getSecretKey())
                .subject(username)
                .issuedAt(new Date())
                .expiration(expiration)
                .claims(claims)
                .compact();
    }
}
