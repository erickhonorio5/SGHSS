package gestao.sghss.security.jwt;

import gestao.sghss.domain.User;
import gestao.sghss.security.services.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenUtils {

    private static final String COOKIE_NAME = "token";
    private static final String PATH = "/";
    private static final String PROD_DOMAIN = ".rctsystem.com";
    private static final int COOKIE_MAX_AGE = 216_000;
    
    private final Environment environment;

    @Value("${api.app.jwtSecret}")
    private String jwtSecret;

    @Value("${api.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String getTokenFromCookie(HttpServletRequest request) {
        return Optional.ofNullable(WebUtils.getCookie(request, COOKIE_NAME))
                .map(Cookie::getValue)
                .orElse(null);
    }

    public String getUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String generateTokenFromUser(String id, String username, String name, String email, 
                                       Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("name", name);
        claims.put("email", email);
        if (authorities != null) {
            claims.put("roles", authorities);
        }

        return createToken(claims, username);
    }

    public ResponseCookie generateToken(UserDetailsImpl userDetails) {
        String jwt = generateTokenFromUser(
            userDetails.getId().toString(),
            userDetails.getUsername(),
            userDetails.getFullName(),
            userDetails.getEmail(),
            userDetails.getAuthorities()
        );
        return createCookie(jwt).build();
    }

    public ResponseCookie generateToken(User user) {
        String jwt = generateTokenFromUser(
            user.getId().toString(),
            user.getUsername(),
            user.getFullName(),
            user.getEmail(),
            null
        );
        return createCookie(jwt).build();
    }

    public ResponseCookie cleanToken() {
        return createCookie("")
                .maxAge(0)
                .build();
    }

    private ResponseCookie.ResponseCookieBuilder createCookie(String value) {
        var builder = ResponseCookie.from(COOKIE_NAME, value)
                .path(PATH)
                .maxAge(COOKIE_MAX_AGE)
                .secure(true)
                .httpOnly(true);

        if (isProdProfile()) {
            builder.domain(PROD_DOMAIN);
        }

        return builder;
    }

    private boolean isProdProfile() {
        return Arrays.asList(environment.getActiveProfiles()).contains("prod");
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT token validation failed: {}", e.getMessage());
            return false;
        }
    }
}