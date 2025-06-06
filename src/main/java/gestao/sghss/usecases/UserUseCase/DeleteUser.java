package gestao.sghss.usecases.UserUseCase;

import gestao.sghss.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteUser {

    private final UserGateway userGateway;
    private final FindUser findUser;

    private static final String ADMIN = "admin";

    public void byIds(final List<Long> ids) {
        userGateway.deleteByIds(ids);
    }

    public void byId(final Long id) {
        userGateway.deleteById(id);
        log.info("[deleteUser][userId deleted: {}]", id);
    }
}
