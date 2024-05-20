package voting.sys.webapp.api.security;

import lombok.Getter;

public class CurrentToken {

    @Getter
    private static String authToken;

    public static void setAuthToken(String token) {
        CurrentToken.authToken = token;
        System.out.println("Auth token set to " + token);
    }

    private CurrentToken() {
        authToken = "";
    }
}
