package voting.sys.webapp.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UsersRequestDto {

    @NotBlank(message = "IDNP is mandatory")
    @Length(max = 13,
            message = "IDNP must consist of max 13 characters")
    private String idnp;

    @NotBlank(message = "Password is mandatory")
    @Length(max = 255,
            message = "Password must consist of max 255 characters")
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "Invalid email address")
    @Length(max = 255,
            message = "Email must consist of max 255 characters")
    private String email;

    @NotBlank(message = "Name is mandatory")
    @Length(max = 100,
            message = "Name must consist of max 100 characters")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Length(max = 100,
            message = "Surname must consist of max 100 characters")
    private String surname;

    @Length(max = 100,
            message = "Patronymic must consist of max 100 characters")
    private String patronymic;

    private Boolean isEnabled;

    private List<String> roles = new ArrayList<>();

}
