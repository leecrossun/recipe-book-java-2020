package controller.review;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.ReviewDAO;
import service.dto.Review;

public class CreateReviewController implements Controller{
	
	private ReviewDAO reviewDAO;

	public CreateReviewController() {
		try {
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();	
		
		String reviewId = request.getParameter("reviewId");
		String userId = UserSessionUtils.getLoginUserId(session);
		String recipeId = request.getParameter("recipeId");
		String recipeName = request.getParameter("recipeName");
		int rating = Integer.parseInt(request.getParameter("rating"));
		
//		Date date_now = new Date(System.currentTimeMillis());
//		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy/MM/dd"); 
//		String published = fourteen_format.format(date_now);
		
		Review review = new Review(
				reviewId, userId, recipeId, recipeName,
				request.getParameter("content"),
				rating,
				null
				);
		
		try {
			reviewDAO.writeMyReview(review);
		} catch (Exception e) {
			e.printStackTrace();
			return "/recipe/view";
		}
		
		return "/recipe/view.jsp";
	}

}
