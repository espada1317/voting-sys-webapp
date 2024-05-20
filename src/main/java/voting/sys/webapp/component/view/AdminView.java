package voting.sys.webapp.component.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.GrantedAuthority;
import voting.sys.webapp.api.security.SecurityService;
import voting.sys.webapp.component.layout.MainLayout;
import voting.sys.webapp.component.navbar.AdministrationTab;

import java.util.List;

import static voting.sys.webapp.util.SecurityConstant.ADMIN_USER_ROLE;

@Route(value = "Admin", layout = MainLayout.class)
@RolesAllowed(ADMIN_USER_ROLE)
public class AdminView extends VerticalLayout implements BeforeEnterObserver {

    private static final SecurityService securityService = new SecurityService();

    public AdminView() {
        AdministrationTab administrationTab = new AdministrationTab();
        add(administrationTab);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (securityService.getAuthenticatedUser() != null
                && securityService.getAuthenticatedUser().getAuthorities() != null) {
            List<String> roles = securityService.getAuthenticatedUser().getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            if (!roles.contains(ADMIN_USER_ROLE)) {
                beforeEnterEvent.rerouteTo("");
            }
        } else {
            beforeEnterEvent.rerouteTo("");
        }
    }

}
