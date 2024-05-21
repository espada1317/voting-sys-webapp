package voting.sys.webapp.api.validator;

import com.vaadin.flow.component.notification.Notification;
import voting.sys.webapp.api.dto.validation.UserValidationDto;

import static voting.sys.webapp.util.ValidatorExceptionConstant.VALIDATOR_USER_EMAIL_EMPTY_MESSAGE;
import static voting.sys.webapp.util.ValidatorExceptionConstant.VALIDATOR_USER_NAME_EMPTY_MESSAGE;
import static voting.sys.webapp.util.ValidatorExceptionConstant.VALIDATOR_USER_PASSWORDS_EMPTY_MESSAGE;
import static voting.sys.webapp.util.ValidatorExceptionConstant.VALIDATOR_USER_PASSWORDS_NOT_MATCH_MESSAGE;
import static voting.sys.webapp.util.ValidatorExceptionConstant.VALIDATOR_USER_SURNAME_EMPTY_MESSAGE;
import static voting.sys.webapp.util.ValidatorExceptionConstant.VALIDATOR_USER_USERNAME_EMPTY_MESSAGE;

public class InsertionValidator {

    public static boolean isValidInsertionNewUser(UserValidationDto validationDto) {

        if (validationDto.getUserIdnp().getTextFieldValue().isEmpty()) {
            Notification.show(VALIDATOR_USER_USERNAME_EMPTY_MESSAGE);
            return false;
        }

        if (validationDto.getUserPassword().getPasswordFieldValue().isEmpty()
                || validationDto.getUserRepeatedPassword().getPasswordFieldValue().isEmpty()) {
            Notification.show(VALIDATOR_USER_PASSWORDS_EMPTY_MESSAGE);
            return false;
        }

        if (!validationDto.getUserPassword().getPasswordFieldValue()
                .equals(validationDto.getUserRepeatedPassword().getPasswordFieldValue())) {
            Notification.show(VALIDATOR_USER_PASSWORDS_NOT_MATCH_MESSAGE);
            return false;
        }

        if (validationDto.getUserEmail().getEmailFieldValue().isEmpty()) {
            Notification.show(VALIDATOR_USER_EMAIL_EMPTY_MESSAGE);
            return false;
        }

        if (validationDto.getUserName().getTextFieldValue().isEmpty()) {
            Notification.show(VALIDATOR_USER_NAME_EMPTY_MESSAGE);
            return false;
        }

        if (validationDto.getUserSurname().getTextFieldValue().isEmpty()) {
            Notification.show(VALIDATOR_USER_SURNAME_EMPTY_MESSAGE);
            return false;
        }

        return true;
    }

    private InsertionValidator() {
    }
}
