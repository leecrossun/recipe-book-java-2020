package controller.refrigerator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import controller.user.UserSessionUtils;
import persistence.dao.RefrigeratorDAO;

public class DeleteIngredientController implements Controller {
	private RefrigeratorDAO refrigeratorDAO;
	private static final Logger logger = LoggerFactory.getLogger(DeleteIngredientController.class);

	public DeleteIngredientController() {
		try {
			refrigeratorDAO = new RefrigeratorDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();	
		String userId = UserSessionUtils.getLoginUserId(session);
		
		logger.debug("UserId : {}, ingId : {}", 
    			userId, request.getParameter("ingId"));
		
		try {
			refrigeratorDAO.deleteUserIngredient(userId, request.getParameter("ingId"));
		} catch (Exception e) {
			
		}
		return "redirect:/refrigerator/view";
	}
}
