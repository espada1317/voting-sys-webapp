package voting.sys.webapp.component.navbar;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import voting.sys.webapp.component.tab.ElectionTab;

import static voting.sys.webapp.util.TabValuesConstant.NAVBAR_ADMINISTRATION_TAB_CANDIDATE_LABEL;

public class UserTab extends VerticalLayout {

    public UserTab() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        tabSheet.add(NAVBAR_ADMINISTRATION_TAB_CANDIDATE_LABEL,
                new ElectionTab());

        add(tabSheet);
    }

}