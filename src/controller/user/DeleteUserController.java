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
	
		if ((UserSessionUtils.isLoginUser("admin", session) && 	// 로그인한 사용자가 관리자이고 	
				 !deleteId.equals("admin"))							// 삭제 대상이 일반 사용자인 경우, 
				   || 												// 또는 
				(!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
				  UserSessionUtils.isLoginUser(deleteId, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
					
				manager.remove(deleteId);				// 사용자 정보 삭제
				if (UserSessionUtils.isLoginUser("admin", session))	// 로그인한 사용자가 관리자 	
					return "redirect:/user/list";		// 사용자 리스트로 이동
				else 									// 로그인한 사용자는 이미 삭제됨
					return "redirect:/user/logout";		// logout 처리
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
