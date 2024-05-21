package voting.sys.webapp.component.input;

import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;
import voting.sys.webapp.api.dto.response.UserRoles;
import voting.sys.webapp.api.service.UsersService;

import java.util.ArrayList;
import java.util.List;

import static voting.sys.webapp.util.DialogValuesConstant.USER_ROLES_LABEL;

@Getter
@CssImport("./styles/dialog-styles.css")
public class MultipleComboBoxData extends VerticalLayout {

    private static final UsersService dmUsersService = new UsersService();

    private List<String> selectedRoles = new ArrayList<>();

    public MultipleComboBoxData() {
        add(getSpan());
        add(getComboBox());
        addClassName("flex-display");
        addClassName("flex-group-role");
    }

    private Span getSpan() {
        return new Span(USER_ROLES_LABEL);
    }

    private MultiSelectComboBox<UserRoles> getComboBox() {
        MultiSelectComboBox<UserRoles> comboBox = new MultiSelectComboBox<>();
        List<UserRoles> userRoles = dmUsersService.getRoles();
        comboBox.setItems(userRoles);
        comboBox.setItemLabelGenerator(UserRoles::getName);
        comboBox.addClassName("combo-box-role");
        comboBox.addValueChangeListener(e -> selectedRoles = e.getValue().stream().map(UserRoles::getName).toList());

        return comboBox;
    }

}
