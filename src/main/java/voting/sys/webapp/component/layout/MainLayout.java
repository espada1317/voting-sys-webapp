package voting.sys.webapp.component.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.core.GrantedAuthority;
import voting.sys.webapp.api.security.SecurityService;
import voting.sys.webapp.component.view.AdminView;
import voting.sys.webapp.component.view.UserView;

import java.util.List;

import static voting.sys.webapp.util.LayoutConstant.MAIN_LAYOUT_PAGE_TITLE;
import static voting.sys.webapp.util.LayoutConstant.NAVBAR_ADMIN_PAGE_TITLE;
import static voting.sys.webapp.util.LayoutConstant.NAVBAR_USER_PAGE_TITLE;
import static voting.sys.webapp.util.SecurityConstant.ADMIN_USER_ROLE;
import static voting.sys.webapp.util.SecurityConstant.USER_USER_ROLE;

@CssImport("./styles/button-styles.css")
public class MainLayout extends AppLayout {

    private static final SecurityService securityService = new SecurityService();

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H4 title = new H4(MAIN_LAYOUT_PAGE_TITLE);
        title.addClickListener(e -> UI.getCurrent().navigate(""));
        Tabs tabs = getTabs();
        addToDrawer(tabs);

        HorizontalLayout headerButtons = new HorizontalLayout();
        headerButtons.addClassName("flex-display-logout");

        if (securityService.getAuthenticatedUser() != null) {
            Button logout = new Button("Logout", click ->
                    securityService.logout());
            headerButtons.add(logout);
            addToNavbar(toggle, title, headerButtons);
        } else {
            addToNavbar(toggle, title);
        }
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();

        if (securityService.getAuthenticatedUser() != null) {
            List<String> roles = securityService.getAuthenticatedUser().getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            if (roles.contains(ADMIN_USER_ROLE)) {
                tabs.add(createTab(VaadinIcon.USER_STAR, NAVBAR_ADMIN_PAGE_TITLE, AdminView.class));
            }
            if (roles.contains(USER_USER_ROLE)) {
                tabs.add(createTab(VaadinIcon.USER_CHECK, NAVBAR_USER_PAGE_TITLE, UserView.class));
            }
        }
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> clazz) {
        Icon icon = viewIcon.create();

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(clazz);
        link.setTabIndex(-1);

        return new Tab(link);
    }
}