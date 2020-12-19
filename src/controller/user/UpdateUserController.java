package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import service.dto.User;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	if (request.getMethod().equals("GET")) {	
    		
    		String updateId = request.getParameter("userId");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		UserManager manager = UserManager.getInstance();
			User user = manager.findUser(updateId);	
			request.setAttribute("user", user);			
			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateId, session) ||
				UserSessionUtils.isAdminUser(session)) {
				
				return "/user/updateForm.jsp";    
			}    
			
			
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 정보는 수정할 수 없습니다"));            
			return "/user/test";	
	    }	
    	
    	
    	User updateUser = new User(
    		request.getParameter("userId"),
    		request.getParameter("password"),
    		request.getParameter("name"),
    		request.getParameter("email"),
    		request.getParameter("phone"));

    	log.debug("Update User : {}", updateUser);

		UserManager manager = UserManager.getInstance();
		manager.update(updateUser);			
        return "redirect:/refrigerator/view";			
    }
}
