package gestao.sghss.security.services;

import gestao.sghss.domain.User;
import gestao.sghss.security.jwt.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public ResponseCookie authenticate(String username, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return tokenUtils.generateToken(userDetails);
    }

    public ResponseCookie generateToken(UserDetailsImpl userDetails) {
        return tokenUtils.generateToken(userDetails);
    }

    public ResponseCookie generateToken(User user) {
        return tokenUtils.generateToken(user);
    }

    public String getUsernameFromRequest(HttpServletRequest request) {
        String token = tokenUtils.getTokenFromCookie(request);
        return token != null ? tokenUtils.getUserName(token) : null;
    }

    public boolean validateTokenFromRequest(HttpServletRequest request) {
        String token = tokenUtils.getTokenFromCookie(request);
        return token != null && tokenUtils.validateToken(token);
    }

    public ResponseCookie logout() {
        return tokenUtils.cleanToken();
    }
}

