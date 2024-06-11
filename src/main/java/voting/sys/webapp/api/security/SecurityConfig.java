package voting.sys.webapp.api.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import voting.sys.webapp.component.view.LoginView;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Autowired
    private RedirectAuthSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/VAADIN/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/actuator")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/**")).authenticated()
                )
                .authenticationProvider(authenticationProvider());

        super.configure(http);
        setLoginView(http, LoginView.class);

        http.formLogin(
                login -> login
                        .permitAll()
                        .loginProcessingUrl("/login")
                        .successHandler(successHandler)
        );
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

