package controller.refrigerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.RefrigeratorDAO;

public class AddFavoriteController implements Controller{
	private RefrigeratorDAO refrigeratorDAO;

	public AddFavoriteController() {
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
		String recipeId = request.getParameter("recipeId");
		
		try {
			refrigeratorDAO.addFavoriteRecipe(userId, recipeId);
			request.setAttribute("recipeId", recipeId);
		} catch (Exception e) {
			
		}
		return "/recipe/view";
	}
}
