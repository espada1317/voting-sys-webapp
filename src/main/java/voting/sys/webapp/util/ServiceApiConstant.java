package voting.sys.webapp.util;

public class ServiceApiConstant {

    public static final String API_POST_AUTH_URL = "http://localhost:8080/authenticate";

    public static final String API_GET_USER_BY_IDNP_URL = "http://localhost:8080/users/";
    public static final String API_GET_ALL_USERS_URL = "http://localhost:8080/users";
    public static final String API_GET_ALL_USER_ROLES_URL = "http://localhost:8080/users/roles";
    public static final String API_POST_USER_URL = "http://localhost:8080/users";
    public static final String API_PUT_USER_ENABLEMENT_URL = "http://localhost:8080/users/enablement/";
    public static final String API_PUT_USER_RESET_PASS_URL = "http://localhost:8080/users/resetPassword/";

    public static final String API_GET_ELECTION_LIST_URL = "http://localhost:8081/election";
    public static final String API_POST_ELECTION_VOTE_URL = "http://localhost:8081/election/save";
    public static final String API_GET_ELECTION_CHECK_VOTE_URL = "http://localhost:8081/election/checkVote/";

    private ServiceApiConstant() {
    }
}
