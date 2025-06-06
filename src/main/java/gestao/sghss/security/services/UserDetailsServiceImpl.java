package gestao.sghss.security.services;

import gestao.sghss.configs.security.constants.SecurityConstants;
import gestao.sghss.usecases.UserUseCase.FindUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final FindUser findUser;

    @Override
    public UserDetails loadUserByUsername(final String loginOrEmail) throws UsernameNotFoundException {
        final var user = findUser.byUsernameOrEmail(loginOrEmail);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + loginOrEmail);
        }

        var authorities = Boolean.TRUE.equals(user.getAdmin()) 
            ? SecurityConstants.ADMIN_AUTHORITIES
            : SecurityConstants.USER_AUTHORITIES;

        return UserDetailsImpl.build(user, AuthorityUtils.createAuthorityList(
            authorities.toArray(new String[0])
        ));
    }
}