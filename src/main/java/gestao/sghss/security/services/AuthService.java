package gestao.sghss.security.services;

import gestao.sghss.domain.User;
import gestao.sghss.security.jwt.TokenUtils;
import gestao.sghss.usecases.UserUseCase.FindUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final FindUser findUser;

    public ResponseCookie authenticate(String username, String password) {
        log.info("Tentando autenticar usuário: {}", username);
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        final var user = findUser.byUsernameOrEmail(username);
        user.setLastAccess();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Usuário autenticado com sucesso: {}", userDetails.getUsername());

        return tokenUtils.generateToken(userDetails);
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

