package controller.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import persistence.dao.IngredientDAO;
import persistence.dao.RecipeDAO;
import persistence.dao.ReviewDAO;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;
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
		
		String recipeId;
		
		if (request.getParameter("recipeId") != null)
			recipeId = request.getParameter("recipeId");
		else
			recipeId = (String) request.getAttribute("recipeId");
		
		Recipe recipe = recipeDAO.findRecipeById(recipeId);
		List<RecipeIngredient> rcpIng = recipeDAO.findRcpIngById(recipeId);
		List<RecipeStep> rcpStep = recipeDAO.findRcpStepById(recipeId);
		List<Review> review = reviewDAO.findReviewByRecipeId(recipeId);
		
		request.setAttribute("recipe", recipe);
		request.setAttribute("rcpIng", rcpIng);
		request.setAttribute("rcpStep", rcpStep);
		request.setAttribute("reviews", review);
		
		return "/recipe/viewRecipe.jsp";
	}
	

}
