package voting.sys.webapp.api.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import voting.sys.webapp.api.dto.response.ElectionItemResponseDto;
import voting.sys.webapp.api.exception.BadRequestException;
import voting.sys.webapp.api.exception.ExceptionResponse;
import voting.sys.webapp.api.security.CurrentToken;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static voting.sys.webapp.util.ServiceApiConstant.API_GET_ELECTION_LIST_URL;

public class ElectionService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<ElectionItemResponseDto> getElectionItems() {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(API_GET_ELECTION_LIST_URL,
                                    HttpMethod.GET,
                                    setHeader(),
                                    ElectionItemResponseDto[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequestException(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    private HttpEntity<String> setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(CurrentToken.getAuthToken());
        return new HttpEntity<>(headers);
    }

}
