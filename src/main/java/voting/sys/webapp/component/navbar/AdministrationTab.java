package voting.sys.webapp.component.navbar;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;

import static voting.sys.webapp.util.TabValuesConstant.NAVBAR_ADMINISTRATION_TAB_INSTITUTION_LABEL;
import static voting.sys.webapp.util.TabValuesConstant.NAVBAR_ADMINISTRATION_TAB_USER_LABEL;

public class AdministrationTab extends VerticalLayout {

    public AdministrationTab() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        tabSheet.add(NAVBAR_ADMINISTRATION_TAB_INSTITUTION_LABEL,
                new Div());
        tabSheet.add(NAVBAR_ADMINISTRATION_TAB_USER_LABEL,
                new Div());

        add(tabSheet);
    }
}
