package voting.sys.webapp.component.tab;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.vaadin.crudui.crud.impl.GridCrud;
import voting.sys.webapp.api.dto.response.UsersResponseDto;
import voting.sys.webapp.api.service.UsersService;
import voting.sys.webapp.component.dialog.UserInsertionDialog;

import java.util.Optional;

import static voting.sys.webapp.util.TabValuesConstant.USER_GRID_EMAIL_COLUMN;
import static voting.sys.webapp.util.TabValuesConstant.USER_GRID_ENABLEMENT_COLUMN;
import static voting.sys.webapp.util.TabValuesConstant.USER_GRID_NAME_SURNAME_COLUMN;
import static voting.sys.webapp.util.TabValuesConstant.USER_GRID_USERNAME_COLUMN;
import static voting.sys.webapp.util.TabValuesConstant.USER_TAB_CONTEXT_MENU_ENABLE_LABEL;
import static voting.sys.webapp.util.TabValuesConstant.USER_TAB_CONTEXT_MENU_RESET_LABEL;
import static voting.sys.webapp.util.TabValuesConstant.USER_TAB_NEW_BUTTON_LABEL;

@CssImport("./styles/users-tab-styles.css")
public class UsersTablePage extends Div {

    private static final UsersService usersService = new UsersService();

    private static GridCrud<UsersResponseDto> usersGrid;

    public UsersTablePage() {
        add(getGrid());
        add(getNewUserButton());
    }

    public static void refreshGrid() {
        usersGrid.refreshGrid();
    }

    private Button getNewUserButton() {
        Button button = new Button(USER_TAB_NEW_BUTTON_LABEL);
        button.addClassName("button-top-padding");
        button.addClickListener(buttonClickEvent -> new UserInsertionDialog());

        return button;
    }

    private static GridCrud<UsersResponseDto> getGrid() {
        usersGrid = new GridCrud<>(UsersResponseDto.class);

        usersGrid.getGrid().removeAllColumns();

        usersGrid.getGrid()
                .addColumn(UsersResponseDto::getIdnp)
                .setHeader(USER_GRID_USERNAME_COLUMN)
                .setSortable(true)
                .setResizable(true);

        usersGrid.getGrid()
                .addColumn(userDto -> userDto.getName() + " " + userDto.getSurname())
                .setHeader(USER_GRID_NAME_SURNAME_COLUMN)
                .setSortable(true)
                .setResizable(true);

        usersGrid.getGrid()
                .addColumn(UsersResponseDto::getEmail)
                .setHeader(USER_GRID_EMAIL_COLUMN)
                .setSortable(true)
                .setResizable(true);

        usersGrid.getGrid()
                .addColumn(new ComponentRenderer<>(userDto -> {
                    Checkbox checkbox = new Checkbox();
                    checkbox.setValue(userDto.getIsEnabled());
                    checkbox.addValueChangeListener(event -> userDto.setIsEnabled(checkbox.getValue()));
                    checkbox.setReadOnly(true);
                    return checkbox;
                }))
                .setHeader(USER_GRID_ENABLEMENT_COLUMN);

        usersGrid.setFindAllOperation(usersService::getAll);
        usersGrid.setAddOperationVisible(false);
        usersGrid.setUpdateOperationVisible(false);
        usersGrid.setDeleteOperationVisible(false);

        usersGrid.getCrudFormFactory().setUseBeanValidation(true);

        GridContextMenu<UsersResponseDto> gridContextMenu = usersGrid.getGrid().addContextMenu();
        gridContextMenu.addItem(USER_TAB_CONTEXT_MENU_RESET_LABEL, event -> {
            Optional<UsersResponseDto> itemOptional = event.getItem();
            if (itemOptional.isPresent()) {
                usersService.resetPassword(itemOptional.get().getId());
                refreshGrid();
            }
        });
        gridContextMenu.addItem(USER_TAB_CONTEXT_MENU_ENABLE_LABEL, event -> {
            Optional<UsersResponseDto> itemOptional = event.getItem();
            if (itemOptional.isPresent()) {
                usersService.modifyEnablement(itemOptional.get().getId());
                refreshGrid();
            }
        });

        return usersGrid;
    }
}