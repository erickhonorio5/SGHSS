package gestao.sghss.usecases.UserUseCase;

import gestao.sghss.domain.User;
import gestao.sghss.exceptions.PermissionDeniedException;
import gestao.sghss.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UpdateUser {

    private final UserGateway userGateway;
    private final FindUser findUser;

    private static final String ADMIN = "admin";

    public User updateUser(final User user) {
        final var existingUser = findUser.byId(user.getId());

        if (isAdmin(existingUser) && !user.getUsername().equalsIgnoreCase(ADMIN)) {
            throw new PermissionDeniedException("You cannot change the developer login");
        }

        return userGateway.update(user);
    }

    public void updateUserPassword(final User user, final User userInDb) {
        if (!userInDb.getPasswordHash().equals(user.getPasswordHash())) {
            throw new PermissionDeniedException("Incorrect password!");
        }

        if (isAdmin(user)) {
            throw new PermissionDeniedException("You cannot change a developer password");
        }

        userGateway.update(user);
    }

    public void updatePassword(final User user) {
        if (isAdmin(user)) {
            throw new PermissionDeniedException("You cannot change a developer password");
        }

        userGateway.update(user);
    }

    private boolean isAdmin(final User user) {
        return ADMIN.equalsIgnoreCase(user.getUsername());
    }
}

