package controller.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import persistence.dao.RecipeDAO;
import persistence.dao.ReviewDAO;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.Review;

public class ViewRecipeController implements Controller{
	
	private RecipeDAO recipeDAO;
	private ReviewDAO reviewDAO;
	
	public ViewRecipeController() {
		try {
			recipeDAO = new RecipeDAO();
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String recipeId = request.getParameter("recipeId");
		Recipe recipe = recipeDAO.findRecipeById(recipeId);
		List<Review> review = reviewDAO.findReviewByRecipeId(recipeId);
		request.setAttribute("recipe", recipe);
		request.setAttribute("review", review);
		
		return "/recipe/viewRecipe.jsp";
	}

}
