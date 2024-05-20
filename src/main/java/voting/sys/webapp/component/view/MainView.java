package voting.sys.webapp.component.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import voting.sys.webapp.component.layout.MainLayout;

@Route(value = "", layout = MainLayout.class)
@PermitAll
public class MainView extends VerticalLayout {

    MainView() {
        add(new H1("Salve, Cesare!!"));
    }

}