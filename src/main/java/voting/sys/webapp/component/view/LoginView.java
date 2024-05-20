package voting.sys.webapp.component.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.web.client.ResourceAccessException;
import voting.sys.webapp.api.dto.request.AuthRequestDto;
import voting.sys.webapp.api.exception.BadRequestException;
import voting.sys.webapp.api.security.AuthUserServiceImpl;
import voting.sys.webapp.api.security.CurrentToken;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    private static final AuthUserServiceImpl authenticateUserService = new AuthUserServiceImpl();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        login.setAction("login");
        login.addLoginListener(loginEvent -> {
            try {
                CurrentToken.setAuthToken(
                        authenticateUserService.retrieveJwtToken(
                                new AuthRequestDto(
                                        loginEvent.getUsername(),
                                        loginEvent.getPassword()
                                )
                        )
                );
            } catch (BadRequestException | ResourceAccessException e) {
                loginEvent.getSource().setError(true);
            }
        });

        add(login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
        UI.getCurrent().navigate("/");
    }

}
