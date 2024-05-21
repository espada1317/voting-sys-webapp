package voting.sys.webapp.component.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import voting.sys.webapp.api.dto.request.UsersRequestDto;
import voting.sys.webapp.api.dto.validation.UserValidationDto;
import voting.sys.webapp.api.service.UsersService;
import voting.sys.webapp.api.validator.InsertionValidator;
import voting.sys.webapp.component.input.CheckBoxComponent;
import voting.sys.webapp.component.input.EmailFieldComponent;
import voting.sys.webapp.component.input.MultipleComboBoxData;
import voting.sys.webapp.component.input.PasswordFieldComponent;
import voting.sys.webapp.component.input.TextFieldComponent;
import voting.sys.webapp.component.tab.UsersTablePage;

import static voting.sys.webapp.util.DialogValuesConstant.CANCEL_BUTTON_TEXT;
import static voting.sys.webapp.util.DialogValuesConstant.SAVE_BUTTON_TEXT;
import static voting.sys.webapp.util.DialogValuesConstant.USER_DIALOG_HEADER;
import static voting.sys.webapp.util.DialogValuesConstant.USER_EMAIL_CHAR_LIMIT;
import static voting.sys.webapp.util.DialogValuesConstant.USER_EMAIL_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_EMAIL_PLACEHOLDER_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_ENABLEMENT_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_NAME_CHAR_LIMIT;
import static voting.sys.webapp.util.DialogValuesConstant.USER_NAME_IS_REQUIRED;
import static voting.sys.webapp.util.DialogValuesConstant.USER_NAME_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_NAME_PLACEHOLDER_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_PASSWORD_CHAR_LIMIT;
import static voting.sys.webapp.util.DialogValuesConstant.USER_PASSWORD_IS_REVEAL_VISIBLE;
import static voting.sys.webapp.util.DialogValuesConstant.USER_PASSWORD_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_PASSWORD_PLACEHOLDER_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_PATRONYMIC_CHAR_LIMIT;
import static voting.sys.webapp.util.DialogValuesConstant.USER_PATRONYMIC_IS_REQUIRED;
import static voting.sys.webapp.util.DialogValuesConstant.USER_PATRONYMIC_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_PATRONYMIC_PLACEHOLDER_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_REPEATED_PASSWORD_IS_REVEAL_VISIBLE;
import static voting.sys.webapp.util.DialogValuesConstant.USER_REPEATED_PASSWORD_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_SURNAME_CHAR_LIMIT;
import static voting.sys.webapp.util.DialogValuesConstant.USER_SURNAME_IS_REQUIRED;
import static voting.sys.webapp.util.DialogValuesConstant.USER_SURNAME_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_SURNAME_PLACEHOLDER_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_USERNAME_CHAR_LIMIT;
import static voting.sys.webapp.util.DialogValuesConstant.USER_USERNAME_IS_REQUIRED;
import static voting.sys.webapp.util.DialogValuesConstant.USER_USERNAME_LABEL;
import static voting.sys.webapp.util.DialogValuesConstant.USER_USERNAME_PLACEHOLDER_LABEL;

@CssImport("./styles/dialog-styles.css")
public class UserInsertionDialog {

    private static final UsersService usersService = new UsersService();

    private final TextFieldComponent userIdnp;

    private final PasswordFieldComponent userPassword;

    private final PasswordFieldComponent userRepeatedPassword;

    private final EmailFieldComponent userEmail;

    private final TextFieldComponent userName;

    private final TextFieldComponent userSurname;

    private final TextFieldComponent userPatronymic;

    private final MultipleComboBoxData userRoles;

    private final CheckBoxComponent userEnabled;

    private Dialog dialog;

    public UserInsertionDialog() {
        userIdnp = new TextFieldComponent(
                USER_USERNAME_LABEL,
                USER_USERNAME_PLACEHOLDER_LABEL,
                USER_USERNAME_CHAR_LIMIT,
                USER_USERNAME_IS_REQUIRED
        );
        userPassword = new PasswordFieldComponent(
                USER_PASSWORD_LABEL,
                USER_PASSWORD_PLACEHOLDER_LABEL,
                USER_PASSWORD_CHAR_LIMIT,
                USER_PASSWORD_IS_REVEAL_VISIBLE
        );
        userRepeatedPassword = new PasswordFieldComponent(
                USER_REPEATED_PASSWORD_LABEL,
                USER_PASSWORD_PLACEHOLDER_LABEL,
                USER_PASSWORD_CHAR_LIMIT,
                USER_REPEATED_PASSWORD_IS_REVEAL_VISIBLE
        );
        userEmail = new EmailFieldComponent(
                USER_EMAIL_LABEL,
                USER_EMAIL_PLACEHOLDER_LABEL,
                USER_EMAIL_CHAR_LIMIT
        );
        userName = new TextFieldComponent(
                USER_NAME_LABEL,
                USER_NAME_PLACEHOLDER_LABEL,
                USER_NAME_CHAR_LIMIT,
                USER_NAME_IS_REQUIRED
        );
        userSurname = new TextFieldComponent(
                USER_SURNAME_LABEL,
                USER_SURNAME_PLACEHOLDER_LABEL,
                USER_SURNAME_CHAR_LIMIT,
                USER_SURNAME_IS_REQUIRED
        );
        userPatronymic = new TextFieldComponent(
                USER_PATRONYMIC_LABEL,
                USER_PATRONYMIC_PLACEHOLDER_LABEL,
                USER_PATRONYMIC_CHAR_LIMIT,
                USER_PATRONYMIC_IS_REQUIRED
        );
        userRoles = new MultipleComboBoxData();
        userEnabled = new CheckBoxComponent(
                USER_ENABLEMENT_LABEL
        );
        getDialog();
    }

    private void getDialog() {
        Header header = new Header();
        header.setText(USER_DIALOG_HEADER);
        header.addClassName("dialog-header");

        dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);
        dialog.setCloseOnEsc(true);
        dialog.addClassName("dialog-user-size");
        dialog.add(header);
        dialog.add(getHorizontalLine());

        VerticalLayout dialogContent = new VerticalLayout();
        dialogContent.add(userIdnp);
        dialogContent.add(userPassword);
        dialogContent.add(userRepeatedPassword);
        dialogContent.add(userEmail);
        dialogContent.add(userName);
        dialogContent.add(userSurname);
        dialogContent.add(userPatronymic);
        dialogContent.add(userRoles);
        dialogContent.add(userEnabled);
        dialogContent.add(getSaveDeleteButtons());
        dialog.add(dialogContent);

        dialog.open();
    }

    private Div getHorizontalLine() {
        Div horizontalLine = new Div();
        horizontalLine.addClassName("horizontal-line");

        return horizontalLine;
    }

    private HorizontalLayout getSaveDeleteButtons() {
        Button saveButton = getSaveButton();
        Button cancelButton = getCancelButton();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addClassName("flex-display");
        horizontalLayout.add(saveButton);
        horizontalLayout.add(cancelButton);
        return horizontalLayout;
    }

    private Button getSaveButton() {
        Button saveButton = new Button(SAVE_BUTTON_TEXT);
        saveButton.addClickListener(buttonClickEvent -> {
            if (InsertionValidator.isValidInsertionNewUser(retrieveUserValidationDto())) {
                UsersRequestDto dmUsersRequestDto =
                        UsersRequestDto.builder()
                                .idnp(userIdnp.getTextFieldValue())
                                .password(userPassword.getPasswordFieldValue())
                                .email(userEmail.getEmailFieldValue())
                                .name(userName.getTextFieldValue())
                                .surname(userSurname.getTextFieldValue())
                                .patronymic(userPatronymic.getTextFieldValue())
                                .roles(userRoles.getSelectedRoles())
                                .isEnabled(userEnabled.getCheckBoxValue())
                                .build();

                if (usersService.save(dmUsersRequestDto)) {
                    UsersTablePage.refreshGrid();
                    dialog.close();
                }
            }
        });
        return saveButton;
    }

    private Button getCancelButton() {
        Button cancelButton = new Button(CANCEL_BUTTON_TEXT);
        cancelButton.addClassName("cancel-button");
        cancelButton.addClickListener(buttonClickEvent -> this.dialog.close());
        return cancelButton;
    }

    private UserValidationDto retrieveUserValidationDto() {
        return UserValidationDto.builder()
                .userIdnp(userIdnp)
                .userPassword(userPassword)
                .userRepeatedPassword(userRepeatedPassword)
                .userEmail(userEmail)
                .userName(userName)
                .userSurname(userSurname)
                .build();
    }

}
