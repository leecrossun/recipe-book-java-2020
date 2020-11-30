package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.RecipeDAO;
import service.dto.Recipe;

public class FindByRcpNameController implements Controller{
	
	private RecipeDAO recipeDAO;
	
	public FindByRcpNameController() {
		try {
			recipeDAO = new RecipeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String recipeName = request.getParameter("recipeName");
		
		List<Recipe> recipeList = recipeDAO.getRecipeListByName(recipeName);
		
		request.setAttribute("SearchedRcps", recipeList);
		
		return "/recipe/findByRecipe.jsp";
	}
}
