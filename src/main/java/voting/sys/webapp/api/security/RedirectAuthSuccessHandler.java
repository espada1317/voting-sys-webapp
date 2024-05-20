package voting.sys.webapp.api.security;

import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.IOException;

@SpringComponent
@ApplicationScope
public class RedirectAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final String location;

    @Autowired
    private ServletContext servletContext;

    public RedirectAuthSuccessHandler() {
        location = "/";
    }

    private String getAbsoluteUrl(String url) {
        final String relativeUrl;
        if (url.startsWith("/")) {
            relativeUrl = url.substring(1);
        } else {
            relativeUrl = url;
        }
        return servletContext.getContextPath() + "/" + relativeUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        request.getSession().setAttribute(User.class.getName(),
                request.getUserPrincipal());
        response.sendRedirect(getAbsoluteUrl(location));
    }

}
