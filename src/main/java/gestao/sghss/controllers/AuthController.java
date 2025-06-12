package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.requests.SignInRequestDTO;
import gestao.sghss.controllers.dtos.responses.MessageResponse;
import gestao.sghss.security.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Tag(name = "Auth Controller")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login de usuários")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequestDTO loginRequest) {
        final var responseCookie = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ok().header(SET_COOKIE, responseCookie.toString()).body(null);
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logoutUser() {
        ResponseCookie cookie = authService.logout();
        return ResponseEntity.ok().header(SET_COOKIE, cookie.toString()).body(new MessageResponse("Logout realizado com sucesso!"));
    }
}
