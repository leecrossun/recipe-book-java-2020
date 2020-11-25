package controller.refrigerator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.RefrigeratorDAO;

public class DeleteFavoriteController implements Controller {
	private RefrigeratorDAO refrigeratorDAO;

	public DeleteFavoriteController() {
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
		
		try {
			refrigeratorDAO.deletFavoriteRecipe(userId, request.getParameter("recipeId"));
		} catch (Exception e) {
			
		}
		return "redirect:/refrigerator/view";
	}
}
