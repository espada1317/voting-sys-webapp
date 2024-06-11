package voting.sys.webapp.component.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import voting.sys.webapp.api.security.SecurityService;
import voting.sys.webapp.component.layout.MainLayout;
import voting.sys.webapp.component.navbar.UserTab;

import java.util.List;

import static voting.sys.webapp.util.SecurityConstant.USER_USER_ROLE;

@Route(value = "User", layout = MainLayout.class)
@RolesAllowed(USER_USER_ROLE)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserView extends VerticalLayout implements BeforeEnterObserver {

    private static final SecurityService securityService = new SecurityService();

    public UserView() {
        UserTab userTab = new UserTab();
        add(userTab);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (securityService.getAuthenticatedUser() != null
                && securityService.getAuthenticatedUser().getAuthorities() != null) {
            List<String> roles = securityService.getAuthenticatedUser().getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            if (!roles.contains(USER_USER_ROLE)) {
                beforeEnterEvent.rerouteTo("");
            }
        } else {
            beforeEnterEvent.rerouteTo("");
        }
    }
}
