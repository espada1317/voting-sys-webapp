package voting.sys.webapp.api.exception;

import com.vaadin.flow.component.notification.Notification;

public class ExceptionResponseUtil {

    public static void parseInvalidColumns(String message) {
        String[] tokens = message.split(":");
        String errorMessage = tokens[2].trim()
                .replace("\"{", "")
                .replace("}\"", "")
                .replace("\"", "");
        Notification.show(errorMessage);
    }

    private ExceptionResponseUtil() {
    }
}