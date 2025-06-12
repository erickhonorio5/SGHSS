package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.requests.SignUpRequestDTO;
import gestao.sghss.controllers.dtos.responses.MessageResponse;
import gestao.sghss.controllers.dtos.responses.UserResponseDTO;
import gestao.sghss.controllers.mappers.UserControllerMapper;
import gestao.sghss.usecases.UserUseCase.CreateUser;
import gestao.sghss.usecases.UserUseCase.FindUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.badRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Tag(name = "Profile Controller")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final FindUser findUser;
    private final CreateUser createUser;
    private final UserControllerMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        if (TRUE.equals(findUser.existsByUsername(signUpRequestDTO.getUsername()))) {
            return badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (TRUE.equals(findUser.existsByEmail(signUpRequestDTO.getEmail()))) {
            return badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        createUser.execute(userMapper.toDomainRegister(signUpRequestDTO));
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        var user = findUser.byUsernameOrEmail(userDetails.getUsername());
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }
}
