package voting.sys.webapp.util;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DialogValuesConstant {

    public static final String SAVE_BUTTON_TEXT = "Save";
    public static final String CANCEL_BUTTON_TEXT = "Cancel";

    public static final String USER_USERNAME_LABEL = "*IDNP:";
    public static final String USER_USERNAME_PLACEHOLDER_LABEL = "2XXXXXXXXXXXX";
    public static final Integer USER_USERNAME_CHAR_LIMIT = 13;
    public static final Boolean USER_USERNAME_IS_REQUIRED = TRUE;

    public static final String USER_PASSWORD_LABEL = "*Password:";
    public static final String USER_PASSWORD_PLACEHOLDER_LABEL = "";
    public static final Integer USER_PASSWORD_CHAR_LIMIT = 255;
    public static final Boolean USER_PASSWORD_IS_REVEAL_VISIBLE = TRUE;

    public static final String USER_REPEATED_PASSWORD_LABEL = "*Confirm:";
    public static final Boolean USER_REPEATED_PASSWORD_IS_REVEAL_VISIBLE = FALSE;

    public static final String USER_EMAIL_LABEL = "*Email:";
    public static final String USER_EMAIL_PLACEHOLDER_LABEL = "XXXX@XXX.XX";
    public static final Integer USER_EMAIL_CHAR_LIMIT = 255;

    public static final String USER_NAME_LABEL = "*Name:";
    public static final String USER_NAME_PLACEHOLDER_LABEL = "";
    public static final Integer USER_NAME_CHAR_LIMIT = 100;
    public static final Boolean USER_NAME_IS_REQUIRED = TRUE;

    public static final String USER_SURNAME_LABEL = "*Surname:";
    public static final String USER_SURNAME_PLACEHOLDER_LABEL = "";
    public static final Integer USER_SURNAME_CHAR_LIMIT = 100;
    public static final Boolean USER_SURNAME_IS_REQUIRED = TRUE;

    public static final String USER_PATRONYMIC_LABEL = "Patronymic:";
    public static final String USER_PATRONYMIC_PLACEHOLDER_LABEL = "";
    public static final Integer USER_PATRONYMIC_CHAR_LIMIT = 100;
    public static final Boolean USER_PATRONYMIC_IS_REQUIRED = FALSE;

    public static final String USER_ENABLEMENT_LABEL = "Enabled";
    public static final String USER_ROLES_LABEL = "User roles:";

    public static final String USER_DIALOG_HEADER = "New User";

    private DialogValuesConstant() {
    }
}
