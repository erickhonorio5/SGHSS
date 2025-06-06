package gestao.sghss.usecases.UserUseCase;

import gestao.sghss.domain.User;
import gestao.sghss.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUser {

    private final UserGateway userGateway;
    private final ApplicationEventPublisher eventPublisher;

    public User execute(final User user) {
        log.info("[create-user: {}] creating new user", user.getUsername());

        final User save = userGateway.save(user);

        eventPublisher.publishEvent(save);
        return save;
    }
}
