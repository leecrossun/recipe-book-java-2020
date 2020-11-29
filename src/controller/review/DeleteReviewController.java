package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import persistence.dao.ReviewDAO;
import service.dto.Review;

public class DeleteReviewController implements Controller{
	
	private ReviewDAO reviewDAO;

	public DeleteReviewController() {
		try {
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String reviewId = request.getParameter("reviewId");
		Review review = reviewDAO.findReviewByReviewId(reviewId);
			
		try {
			//System.out.println(userId + recipeId +"(레시피아이디)" + content + rating); // 파라미터 확인
			reviewDAO.deleteMyReview(review);
			return "/recipe/view";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/refrigerator/view";

		}

	}



}
