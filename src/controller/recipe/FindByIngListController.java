package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import persistence.dao.RecipeDAO;
import service.dto.Recipe;

public class FindByIngListController implements Controller{
	
	private RecipeDAO recipeDAO;
	
	public FindByIngListController() {
		try {
			recipeDAO = new RecipeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String recipeName = request.getParameter("ingList");
		
		List<Recipe> recipeList = recipeDAO.getRecipeListByName(recipeName);
		
		request.setAttribute("SearchedRcps", recipeList);
		
		return "/recipe/findByIngredient2.jsp";
		
	}
	
	
}
