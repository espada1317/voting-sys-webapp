package voting.sys.webapp.api.dto.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import voting.sys.webapp.component.input.EmailFieldComponent;
import voting.sys.webapp.component.input.PasswordFieldComponent;
import voting.sys.webapp.component.input.TextFieldComponent;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserValidationDto {

    private TextFieldComponent userIdnp;

    private PasswordFieldComponent userPassword;

    private PasswordFieldComponent userRepeatedPassword;

    private EmailFieldComponent userEmail;

    private TextFieldComponent userName;

    private TextFieldComponent userSurname;

}
