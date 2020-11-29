package controller.recipe;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.RecipeDAO;
import service.dto.Recipe;

public class ViewMyRecipeController implements Controller{
private RecipeDAO recipeDAO = new RecipeDAO();
	
	public ViewMyRecipeController() {
		try {
			recipeDAO = new RecipeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();	
		String userId = UserSessionUtils.getLoginUserId(session);
		
		//String userId = request.getParameter("userId");
		ArrayList<Recipe> myRecipeList = (ArrayList<Recipe>) recipeDAO.getMyRecipeList(userId);
		request.setAttribute("myRecipeList", myRecipeList);
		return "/recipe/viewMyRecipeList.jsp";
	}

}
