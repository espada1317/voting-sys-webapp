package voting.sys.webapp.util;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {

    @Value("${voting.sys.auth.module}")
    private String votingSysAuthModule;

    @Value("${voting.sys.election.module}")
    private String votingSysElectionModule;

}
