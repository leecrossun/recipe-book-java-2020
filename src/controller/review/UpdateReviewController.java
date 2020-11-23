package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import persistence.dao.ReviewDAO;
import service.dto.Review;

public class UpdateReviewController implements Controller{
	private ReviewDAO reviewDAO;

	public UpdateReviewController() {
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
			reviewDAO.updateMyReview(review);
		} catch (Exception e) {
			e.printStackTrace();
			return "/recipe/view";
		}
			
		return "redirect:/recipe/view";  // 리뷰가 원래 있던 recipe 로 리다이렉트 (url 확인 후 수정할 것)
	}

}
