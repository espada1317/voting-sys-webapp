package voting.sys.webapp.component.input;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

@Getter
@CssImport("./styles/dialog-styles.css")
public class TextFieldComponent extends HorizontalLayout {

    private TextField textField;

    public TextFieldComponent(String labelName,
                              String placeholder,
                              int charLimit,
                              boolean isRequired) {
        addClassName("right-center-align-input");
        add(new Span(labelName));
        add(buildTextField(placeholder, charLimit, isRequired));
    }

    public String getTextFieldValue() {
        return textField.getValue();
    }

    private TextField buildTextField(String placeholder,
                                   int charLimit,
                                   boolean isRequired) {
        this.textField = new TextField();
        textField.setRequired(isRequired);
        textField.setValue("");
        textField.setPlaceholder(placeholder);
        textField.setMaxLength(charLimit);
        textField.setClearButtonVisible(true);
        textField.addClassName("larger-text-field");

        return textField;
    }

}
