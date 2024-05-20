package voting.sys.webapp.component.navbar;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;

import static voting.sys.webapp.util.TabValuesConstant.NAVBAR_USER_TAB_MANAGE_DOCUMENT_LABEL;
import static voting.sys.webapp.util.TabValuesConstant.NAVBAR_USER_TAB_MANAGE_PROJECT_LABEL;

public class UserTab extends VerticalLayout {

    public UserTab() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        tabSheet.add(NAVBAR_USER_TAB_MANAGE_DOCUMENT_LABEL,
                new Div());
        tabSheet.add(NAVBAR_USER_TAB_MANAGE_PROJECT_LABEL,
                new Div());

        tabSheet.addSelectedChangeListener(selectedChangeEvent -> {
            if (selectedChangeEvent.getSelectedTab().getLabel().equals(NAVBAR_USER_TAB_MANAGE_DOCUMENT_LABEL)) {
                //TODO add tab functionality
            }
            if (selectedChangeEvent.getSelectedTab().getLabel().equals(NAVBAR_USER_TAB_MANAGE_PROJECT_LABEL)) {
                //TODO add tab functionality
            }
        });

        add(tabSheet);
    }

}