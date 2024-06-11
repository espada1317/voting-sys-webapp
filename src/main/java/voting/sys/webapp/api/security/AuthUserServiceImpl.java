package voting.sys.webapp.api.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import voting.sys.webapp.api.dto.request.AuthRequestDto;
import voting.sys.webapp.util.ServiceApiConstant;

@Slf4j
@Service
public class AuthUserServiceImpl {

    private final RestTemplate restTemplate = new RestTemplate();

    public String retrieveJwtToken(AuthRequestDto authRequestDto) {
        try {
            return restTemplate.exchange(ServiceApiConstant.getApiPostAuthUrl(),
                    HttpMethod.POST,
                    setHeader(authRequestDto),
                    String.class).getBody();
        } catch (HttpClientErrorException e) {
            log.error("Error getting token for invalid credential : " + e.getMessage());
        }
        return null;
    }

    private HttpEntity<Object> setHeader(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(object, headers);
    }

}
