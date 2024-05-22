package voting.sys.webapp.api.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import voting.sys.webapp.api.dto.request.ElectionItemRequestDto;
import voting.sys.webapp.api.dto.response.ElectionItemResponseDto;
import voting.sys.webapp.api.exception.BadRequestException;
import voting.sys.webapp.api.exception.ExceptionResponse;
import voting.sys.webapp.api.exception.ExceptionResponseUtil;
import voting.sys.webapp.api.security.CurrentToken;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static voting.sys.webapp.util.ServiceApiConstant.API_GET_ELECTION_CHECK_VOTE_URL;
import static voting.sys.webapp.util.ServiceApiConstant.API_GET_ELECTION_LIST_URL;
import static voting.sys.webapp.util.ServiceApiConstant.API_POST_ELECTION_VOTE_URL;

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

    public boolean saveElectionVote(ElectionItemRequestDto electionItemRequestDto) {
        try {
            restTemplate.exchange(API_POST_ELECTION_VOTE_URL,
                    HttpMethod.POST,
                    setHeader(electionItemRequestDto),
                    Long.class);
        } catch (HttpClientErrorException e) {
            ExceptionResponseUtil.parseInvalidColumns(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkElectionVote(String idnp) {
        try {
            return Boolean.TRUE.equals(
                    Objects.requireNonNull(restTemplate.exchange(API_GET_ELECTION_CHECK_VOTE_URL + idnp,
                                    HttpMethod.GET,
                                    setHeader(),
                                    boolean.class))
                            .getBody());
        } catch (HttpClientErrorException e) {
            ExceptionResponseUtil.parseInvalidColumns(e.getMessage());
        }
        return false;
    }

    private HttpEntity<String> setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(CurrentToken.getAuthToken());
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Object> setHeader(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(CurrentToken.getAuthToken());
        return new HttpEntity<>(object, headers);
    }

}
