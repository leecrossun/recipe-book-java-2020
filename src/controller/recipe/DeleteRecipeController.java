package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.RecipeDAO;
import persistence.dao.ReviewDAO;

public class DeleteRecipeController implements Controller{
	
	private RecipeDAO recipeDAO = new RecipeDAO();
	private ReviewDAO reviewDAO = new ReviewDAO();
	public DeleteRecipeController() {
		try {
			recipeDAO = new RecipeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String recipeId = request.getParameter("recipeId");
		String userId = request.getParameter("userId");
		recipeDAO.deleteRecipeStep(recipeId);
		recipeDAO.deleteRecipeIngredient(recipeId);
		reviewDAO.deleteByRecipeId(recipeId);
		recipeDAO.deleteRecipe(recipeId);
//		if (UserSessionUtils.isAdminUser(session))
//			return "redirect:/recipe/findByRecipe2";
//		else
		//return "redirect:/refrigerator/view.jsp";
		return "/recipe/myList";
		
//		HttpSession session = request.getSession();	
//		
//		if ((UserSessionUtils.isAdminUser(session)) 
//				|| 												// 또는 
//			(UserSessionUtils.isLoginUser(userId, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
//			
//			recipeDAO.deleteRecipe(recipeId);
////			if (UserSessionUtils.isAdminUser(session))
////				return "redirect:/recipe/findByRecipe2";
////			else
//			//return "redirect:/refrigerator/view.jsp";
//			return "redirect:/recipe/myList?userId="+userId;
//			
//		}
//		return "/recipe/findByRecipe1.jsp";
	}
}
