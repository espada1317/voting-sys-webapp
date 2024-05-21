package voting.sys.webapp.component.input;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


@CssImport("./styles/dialog-styles.css")
public class CheckBoxComponent extends HorizontalLayout {

    private static Checkbox checkbox;

    public CheckBoxComponent(String labelName) {
        addClassName("right-center-align-input");
        add(getCheckbox());
        add(new Span(labelName));
    }

    public boolean getCheckBoxValue() {
        return checkbox.getValue();
    }

    private static Checkbox getCheckbox() {
        checkbox = new Checkbox();
        checkbox.setValue(true);
        checkbox.addValueChangeListener(event -> checkbox.setValue(checkbox.getValue()));

        return checkbox;
    }

}
