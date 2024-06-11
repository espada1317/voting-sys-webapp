package voting.sys.webapp.api.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import voting.sys.webapp.api.dto.request.UsersRequestDto;
import voting.sys.webapp.api.dto.response.UserRoles;
import voting.sys.webapp.api.dto.response.UsersResponseDto;
import voting.sys.webapp.api.exception.BadRequestException;
import voting.sys.webapp.api.exception.ExceptionResponse;
import voting.sys.webapp.api.exception.ExceptionResponseUtil;
import voting.sys.webapp.api.security.CurrentToken;
import voting.sys.webapp.util.ServiceApiConstant;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UsersService {

    private final RestTemplate restTemplate = new RestTemplate();

    public UsersResponseDto getUserByIdnp(String idnp) {
        try {
            return Objects.requireNonNull(restTemplate.exchange(ServiceApiConstant.getApiGetUserByIdnpUrl() + idnp,
                            HttpMethod.GET,
                            setHeader(),
                            UsersResponseDto.class)
                    .getBody());
        } catch (HttpClientErrorException e) {
            throw new BadRequestException(Objects.requireNonNull(e.getResponseBodyAs(String.class)));
        }
    }

    public List<UserRoles> getRoles() {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(ServiceApiConstant.getApiGetAllUserRolesUrl(),
                                    HttpMethod.GET,
                                    setHeader(),
                                    UserRoles[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequestException(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public boolean save(UsersRequestDto usersRequestDto) {
        try {
            restTemplate.exchange(ServiceApiConstant.getApiPostUserUrl(),
                    HttpMethod.POST,
                    setHeader(usersRequestDto),
                    UsersRequestDto.class);
        } catch (HttpClientErrorException e) {
            ExceptionResponseUtil.parseInvalidColumns(e.getMessage());
            return false;
        }
        return true;
    }

    public List<UsersResponseDto> getAll() {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(ServiceApiConstant.getApiGetAllUsersUrl(),
                                    HttpMethod.GET,
                                    setHeader(),
                                    UsersResponseDto[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequestException(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public void modifyEnablement(Long id) {
        try {
            restTemplate.exchange(ServiceApiConstant.getApiPutUserEnablementUrl() + id,
                    HttpMethod.PUT,
                    setHeader(),
                    void.class);
        } catch (HttpClientErrorException e) {
            ExceptionResponseUtil.parseInvalidColumns(e.getMessage());
        }
    }

    public void resetPassword(Long id) {
        try {
            restTemplate.exchange(ServiceApiConstant.getApiPutUserResetPassUrl() + id,
                    HttpMethod.PUT,
                    setHeader(),
                    void.class);
        } catch (HttpClientErrorException e) {
            ExceptionResponseUtil.parseInvalidColumns(e.getMessage());
        }
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
