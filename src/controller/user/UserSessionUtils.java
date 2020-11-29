package controller.user;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import service.dto.User;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "userId";
    public static final UserManager usermanager = UserManager.getInstance();
   
    public static String getLoginUserId(HttpSession session) {
        String userId = (String)session.getAttribute(USER_SESSION_KEY);
        return userId;
    }

   
    public static boolean hasLogined(HttpSession session) {
        if (getLoginUserId(session) != null) {
            return true;
        }
        return false;
    }

    public static boolean isAdminUser(HttpSession session) throws SQLException, UserNotFoundException{
    	String userId = getLoginUserId(session);
    	if(userId!=null) {
    		User user = usermanager.findUser(userId);
    		if(user.getAuthority().equals("m"))
    			return true;
    	}
		return false;
    }
    public static boolean isLoginUser(String userId, HttpSession session) throws SQLException, UserNotFoundException {
        String loginUserId = getLoginUserId(session);
        
        if (loginUserId != null) {
        	
        	if(loginUserId.equals(userId)) //세션과 비교하는 userId가 같으면 OK
        		return true;
        }
        return false;
    }
}
