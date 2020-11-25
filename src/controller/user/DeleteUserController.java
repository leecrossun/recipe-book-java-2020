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
    		if (request.getMethod().equals("GET")) {	
    		
    			String deleteId = request.getParameter("userId");
    			log.debug("Delete User : {}", deleteId);

    			UserManager manager = UserManager.getInstance();		
    			HttpSession session = request.getSession();	
    		
    			manager.remove(deleteId);				// 사용자 정보 삭제
				return "redirect:/user/logout";		// logout 처리
    		}
    		return "/user/test";
	}
}
