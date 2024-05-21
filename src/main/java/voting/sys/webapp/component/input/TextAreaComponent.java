package voting.sys.webapp.component.input;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import lombok.Getter;

@Getter
@CssImport("./styles/dialog-styles.css")
public class TextAreaComponent extends VerticalLayout {

    private TextArea textArea;

    public TextAreaComponent(String labelName, String placeholder, int charLimit) {
        addClassName("no-margin-area");
        add(new Span(labelName));
        add(getTextArea(placeholder, charLimit));
    }

    public String getTextAreaValue() {
        return textArea.getValue();
    }

    private TextArea getTextArea(String placeholder, int charLimit) {
        this.textArea = new TextArea();
        textArea.setValue("");
        textArea.setPlaceholder(placeholder);
        textArea.addClassName("right-center-align-input");
        textArea.addClassName("text-area-small-font");
        textArea.setMaxLength(charLimit);

        return textArea;
    }

}
