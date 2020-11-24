package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import persistence.dao.RecipeDAO;
import service.dto.Recipe;
import service.dto.RecipeIngredient;

public class ViewRecipeController implements Controller{
	
	private RecipeDAO recipeDAO;
	
	public ViewRecipeController() {
		try {
			recipeDAO = new RecipeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String recipeId = request.getParameter("recipeId");
		Recipe recipe = recipeDAO.findRecipeById(recipeId);
		request.setAttribute("recipe", recipe);
		
		return "/recipe/viewRecipe.jsp";
	}

}
