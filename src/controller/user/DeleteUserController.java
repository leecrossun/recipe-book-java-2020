package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import service.dto.User;


public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("userId");
    	log.debug("Delete User : {}", deleteId);

		UserManager manager = UserManager.getInstance();		
		HttpSession session = request.getSession();	
	
		if ((UserSessionUtils.isLoginUser("admin", session) && 	
			 !deleteId.equals("admin"))						
			   || 												
			(!UserSessionUtils.isLoginUser("admin", session) &&  
			  UserSessionUtils.isLoginUser(deleteId, session))) { 
				
			manager.remove(deleteId);
			if (UserSessionUtils.isLoginUser("admin", session))	
				return "redirect:/user/list";		
			else 									
				return "redirect:/user/logout";		
		}
		
		
		User user = manager.findUser(deleteId);	
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (UserSessionUtils.isLoginUser("admin", session)) 
				   ? "성공"		
				   : "실패.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/user/view.jsp";		
	}
}
