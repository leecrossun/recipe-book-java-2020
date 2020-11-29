package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import persistence.dao.RecipeDAO;
import persistence.dao.ReviewDAO;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;
import service.dto.Review;

public class ViewRecipeController implements Controller{
	
	private RecipeDAO recipeDAO;
	private ReviewDAO reviewDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ViewRecipeController.class);
	  
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
		String servingString = "1";
		
		if (request.getParameter("recipeId") != null)
			recipeId = request.getParameter("recipeId");
		else
			recipeId = (String) request.getAttribute("recipeId");
		
		Recipe recipe = recipeDAO.findRecipeById(recipeId);
		List<RecipeIngredient> rcpIng = recipeDAO.findRcpIngById(recipeId);
		List<RecipeStep> rcpStep = recipeDAO.findRcpStepById(recipeId);
		List<Review> review = reviewDAO.findReviewByRecipeId(recipeId);
		
		if ((request.getParameter("serving")) != null) {
			servingString = request.getParameter("serving");
			System.out.println(servingString);
			int serving = Integer.parseInt(servingString);
			for (RecipeIngredient r: rcpIng) {
				  r.setAmount(r.getAmount() * serving);
				}
		}
		request.setAttribute("servingString", servingString);
		request.setAttribute("recipe", recipe);
		request.setAttribute("recipeId", recipeId);
		request.setAttribute("rcpIng", rcpIng);
		request.setAttribute("rcpStep", rcpStep);
		request.setAttribute("reviews", review);
		
		return "/recipe/viewRecipe.jsp";
	}
	

}
