package by.broker.http.util;

public class ServletUtil {

    private static final String FULL_PATH="/WEB-INF/jsp/%s.jsp";

    public static String getFullPath(String jspName){
        return String.format(FULL_PATH,jspName);
    }
}
