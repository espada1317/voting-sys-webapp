package voting.sys.webapp.component.input;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;

@CssImport("./styles/dialog-styles.css")
public class EmailFieldComponent extends HorizontalLayout {

    private EmailField emailField;

    public EmailFieldComponent(String labelName,
                               String placeholder,
                               int charLimit) {
        addClassName("right-center-align-input");
        add(new Span(labelName));
        add(getTextField(placeholder, charLimit));
    }

    public String getEmailFieldValue() {
        return emailField.getValue();
    }

    private EmailField getTextField(String placeholder, int charLimit) {
        this.emailField = new EmailField();
        emailField.setRequired(true);
        emailField.setValue("");
        emailField.setPlaceholder(placeholder);
        emailField.setMaxLength(charLimit);
        emailField.setClearButtonVisible(true);
        emailField.addClassName("larger-text-field");

        return emailField;
    }

}
