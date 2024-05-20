package voting.sys.webapp.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UsersResponseDto {

    private Long id;

    private String idnp;

    private String password;

    private String email;

    private Boolean isEnabled;

    private String name;

    private String surname;

    private String patronymic;

    private Set<UserRoles> userRoles;

}

