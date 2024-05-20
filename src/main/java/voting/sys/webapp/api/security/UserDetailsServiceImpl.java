package voting.sys.webapp.api.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import voting.sys.webapp.api.dto.response.UsersResponseDto;
import voting.sys.webapp.api.dto.response.UserRoles;
import voting.sys.webapp.api.exception.BadRequestException;
import voting.sys.webapp.api.service.UsersService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersService usersService = new UsersService();

    @Override
    public UserDetails loadUserByUsername(String idnp) throws UsernameNotFoundException {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            Optional<UsersResponseDto> userInfo = Optional.ofNullable(usersService.getUserByIdnp(idnp));
            if (userInfo.isPresent()) {
                UsersResponseDto usersResponseDto = userInfo.get();
                return new User(
                        usersResponseDto.getIdnp(),
                        usersResponseDto.getPassword(),
                        getAuthorities(usersResponseDto.getUserRoles())
                );
            }
        } catch (BadRequestException badRequestException) {
            log.error("Attempt of authentication with error: " + badRequestException.getMessage());
        }

        throw new UsernameNotFoundException("Username not found!");
    }

    private List<GrantedAuthority> getAuthorities(Set<UserRoles> userRoles) {
        return userRoles.stream()
                .map(UserRoles::getName)
                .map(x -> "ROLE_" + x.toUpperCase().replace(" ", "_"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
