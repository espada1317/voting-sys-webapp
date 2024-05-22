package voting.sys.webapp.component.navbar;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import voting.sys.webapp.component.tab.UsersTablePage;

import static voting.sys.webapp.util.TabValuesConstant.NAVBAR_ADMINISTRATION_TAB_USER_LABEL;

public class AdministrationTab extends VerticalLayout {

    public AdministrationTab() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        tabSheet.add(NAVBAR_ADMINISTRATION_TAB_USER_LABEL,
                new UsersTablePage());

        add(tabSheet);
    }
}
