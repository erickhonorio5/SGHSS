package gestao.sghss.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class UnauthorizedHandler implements AuthenticationEntryPoint {

    private final SecurityUtils securityUtils;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        
        log.error("Erro de autenticação para o path: {}", request.getServletPath(), authException);
        
        securityUtils.addSecurityHeaders(response);
        securityUtils.writeErrorResponse(
            request, 
            response, 
            authException, 
            HttpServletResponse.SC_UNAUTHORIZED
        );
    }
}