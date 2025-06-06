package gestao.sghss.usecases.UserUseCase;

import gestao.sghss.domain.User;
import gestao.sghss.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class FindUser {

    private final UserGateway userGateway;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public User byId(final Long id) {
        return userGateway.findById(id);
    }

    public User byUsernameOrEmail(String usernameOrEmail) {
        boolean isEmail = isEmail(usernameOrEmail);

        if (isEmail) {
            return userGateway.findByEmail(usernameOrEmail);
        } else {
            return userGateway.findByUsername(usernameOrEmail);
        }
    }

    public Boolean existsByUsername(final String username) {
        return userGateway.existsByUsername(username);
    }

    public Boolean existsByEmail(final String email) {
        return userGateway.existsByEmail(email);
    }

    public Page<User> all(Pageable pageable) {
        return userGateway.getAll(pageable);
    }

    public List<User> all() {
        return userGateway.findAll();
    }

    private Boolean isEmail(String usernameOrEmail) {
        if (usernameOrEmail == null) {
            return false;
        }

        return EMAIL_PATTERN.matcher(usernameOrEmail).matches();
    }
}

