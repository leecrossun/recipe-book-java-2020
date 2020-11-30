package controller.refrigerator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.RefrigeratorDAO;
import persistence.dao.ReviewDAO;
import service.dto.Recipe;
import service.dto.Review;
import service.dto.UserIngredient;

public class RefrigeratorController implements Controller{

	private RefrigeratorDAO refrigeratorDAO;
	private ReviewDAO reviewDAO;
	
	public RefrigeratorController() {
		try {
			refrigeratorDAO = new RefrigeratorDAO();
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();	
		String userId = UserSessionUtils.getLoginUserId(session);
		
		List<UserIngredient> userIngredient = refrigeratorDAO.getIngredientList(userId);
		List<UserIngredient> expiredIngredient = new ArrayList<UserIngredient>();
		List<Recipe> favorite = refrigeratorDAO.getFavoriteRecipetList(userId);
		List<Recipe> myRecipe = refrigeratorDAO.getMyRecipetList(userId);
		List<Review> myReview = reviewDAO.findReviewByUserID(userId);
		
		for (int i = 0; i < userIngredient.size(); i++) {
			if (Integer.parseInt(userIngredient.get(i).getRemainingTime()) <= 7) {
				expiredIngredient.add(userIngredient.get(i));
			}
		}
		/*
		 * for (Review r : myReview) { for (int i = 0; i < r.getRating(); i++)
		 * r.setStar(r.getStar() + "⭐"); }
		 */
		request.setAttribute("userIngredient", userIngredient);
		request.setAttribute("expiredIngredients", expiredIngredient);
		request.setAttribute("favorites", favorite);
		request.setAttribute("myRecipes", myRecipe);
		request.setAttribute("myReviews", myReview);
		session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
		
		return "/refrigerator/view.jsp";

	}

}
