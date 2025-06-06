package gestao.sghss.repositories;

import gestao.sghss.gateways.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameIgnoreCase(final String username);

    Boolean existsByUsernameIgnoreCase(final String username);

    Boolean existsByEmailIgnoreCase(final String email);

    Optional<UserEntity> findByEmailIgnoreCase(final String email);
}
