package voting.sys.webapp.component.input;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;

@CssImport("./styles/dialog-styles.css")
public class PasswordFieldComponent extends HorizontalLayout {

    private PasswordField passwordField;

    public PasswordFieldComponent(String labelName,
                                  String placeholder,
                                  int charLimit,
                                  boolean isRevealVisible) {
        addClassName("right-center-align-input");
        add(new Span(labelName));
        add(getTextField(placeholder, charLimit, isRevealVisible));
    }

    public String getPasswordFieldValue() {
        return passwordField.getValue();
    }

    private PasswordField getTextField(String placeholder,
                                       int charLimit,
                                       boolean isRevealVisible) {
        this.passwordField = new PasswordField();
        passwordField.setRequired(true);
        passwordField.setValue("");
        passwordField.setRevealButtonVisible(isRevealVisible);
        passwordField.setPlaceholder(placeholder);
        passwordField.setMaxLength(charLimit);
        passwordField.setClearButtonVisible(true);
        passwordField.addClassName("larger-text-field");

        return passwordField;
    }

}
