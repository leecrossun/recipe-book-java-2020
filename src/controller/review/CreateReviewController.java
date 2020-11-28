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

		String userId = UserSessionUtils.getLoginUserId(session);
		String recipeId = request.getParameter("recipeId");
		String content = request.getParameter("content");
		int rating = Integer.parseInt(request.getParameter("rating"));
		System.out.println(userId + recipeId +"(레시피아이디)" + content + rating); // 파라미터 확인
		Review review = new Review();
		review.setUserId(userId);
		review.setRecipeId(recipeId);
		review.setContent(content);
		review.setRating(rating);

		try {
			reviewDAO.writeMyReview(review);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("recipeId", recipeId);
			return "redirect:/recipe/view";
		}
		request.setAttribute("recipeId", recipeId);
		return "redirect:/recipe/view";
	}

}
