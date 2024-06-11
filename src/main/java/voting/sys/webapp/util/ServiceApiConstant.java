package voting.sys.webapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceApiConstant {

    private static String votingSysAuthModule;
    private static String votingSysElectionModule;

    @Autowired
    public ServiceApiConstant(AppConfig appConfig) {
        votingSysAuthModule = appConfig.getVotingSysAuthModule();
        votingSysElectionModule = appConfig.getVotingSysElectionModule();
    }

    public static String getApiPostAuthUrl() {
        return votingSysAuthModule + "/authenticate";
    }

    public static String getApiGetUserByIdnpUrl() {
        return votingSysAuthModule + "/users/";
    }

    public static String getApiGetAllUsersUrl() {
        return votingSysAuthModule + "/users";
    }

    public static String getApiGetAllUserRolesUrl() {
        return votingSysAuthModule + "/users/roles";
    }

    public static String getApiPostUserUrl() {
        return votingSysAuthModule + "/users";
    }

    public static String getApiPutUserEnablementUrl() {
        return votingSysAuthModule + "/users/enablement/";
    }

    public static String getApiPutUserResetPassUrl() {
        return votingSysAuthModule + "/users/resetPassword/";
    }

    public static String getApiGetElectionListUrl() {
        return votingSysElectionModule + "/election";
    }

    public static String getApiPostElectionVoteUrl() {
        return votingSysElectionModule + "/election/save";
    }

    public static String getApiGetElectionCheckVoteUrl() {
        return votingSysElectionModule + "/election/checkVote/";
    }
}
