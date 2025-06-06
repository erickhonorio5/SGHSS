package gestao.sghss.security.jwt;

import gestao.sghss.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final SecurityUtils securityUtils;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        try {
            processAuthentication(request);
            filterChain.doFilter(request, response);
        } catch (AccessDeniedException e) {
            log.warn("Access denied: {}", e.getMessage());
            securityUtils.writeErrorResponse(
                request, 
                response, 
                e, 
                HttpServletResponse.SC_FORBIDDEN
            );
        } catch (AuthenticationException e) {
            log.warn("Authentication failed: {}", e.getMessage());
            securityUtils.writeErrorResponse(
                request, 
                response, 
                e, 
                HttpServletResponse.SC_UNAUTHORIZED
            );
        } catch (Exception e) {
            log.error("Erro no processamento da autenticação", e);
            securityUtils.writeErrorResponse(
                request, 
                response, 
                e, 
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );
        }
    }

    private void processAuthentication(HttpServletRequest request) {
        String token = tokenUtils.getTokenFromCookie(request);
        
        if (token == null) return;

        if (!tokenUtils.validateToken(token)) {
            throw new BadCredentialsException("Token inválido ou expirado");
        }

        String username = tokenUtils.getUserName(token);
        if (username == null || username.isEmpty()) {
            throw new BadCredentialsException("Token não contém username");
        }

        UserDetails userDetails = loadAndValidateUser(username);
        authenticateUser(userDetails, request);
    }

    private UserDetails loadAndValidateUser(String username) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            if (!userDetails.isEnabled()) {
                throw new DisabledException("Conta de usuário desativada");
            }
            if (!userDetails.isAccountNonLocked()) {
                throw new LockedException("Conta de usuário bloqueada");
            }
            if (!userDetails.isAccountNonExpired()) {
                throw new AccountExpiredException("Conta de usuário expirada");
            }
            if (!userDetails.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException("Credenciais expiradas");
            }

            return userDetails;
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Usuário não encontrado", e);
        }
    }

    private void authenticateUser(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        if (log.isDebugEnabled()) {
            log.debug("Usuário autenticado com sucesso: {}", userDetails.getUsername());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        log.debug("Verificando se deve filtrar a rota: {}", path);
        return isPublicEndpoint(request);
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/api/public/") ||
                path.equals("/api/v1/auth/login") ||
                path.equals("/api/v1/users/signup");
    }
}