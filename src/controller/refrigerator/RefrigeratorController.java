package controller.refrigerator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import persistence.dao.RefrigeratorDAO;
import persistence.dao.ReviewDAO;
import service.dto.Recipe;
import service.dto.Review;
import service.dto.UserIngredient;

public class RefrigeratorController implements Controller{

	private RefrigeratorDAO refrigeratorDAO = new RefrigeratorDAO();
	private ReviewDAO reviewDAO = new ReviewDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		// 로그인 검사 필요한지?
		
		String userId = request.getParameter("userId");
		
		List<UserIngredient> userIngredient = refrigeratorDAO.getIngredientList(userId);
		List<String> remainingTime = refrigeratorDAO.calRemainingTime(userId);
		List<Recipe> favorite = refrigeratorDAO.getFavoriteRecipetList(userId);
		List<Recipe> myRecipe = refrigeratorDAO.getMyRecipetList(userId);
		List<Review> myReview = reviewDAO.findReviewByUserID(userId);
		
		request.setAttribute("userIngredient", userIngredient);
		request.setAttribute("remainingTimes", remainingTime);
		request.setAttribute("favorites", favorite);
		request.setAttribute("myRecipes", myRecipe);
		request.setAttribute("myReviews", myReview);
		
		return "/refrigerator/view.jsp";

	}

}
