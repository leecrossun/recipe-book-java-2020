package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.DeleteUserController;
import controller.user.UserSessionUtils;
import persistence.dao.RecipeDAO;

public class DeleteRecipeController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);
	
	private RecipeDAO recipeDAO = new RecipeDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String recipeId = request.getParameter("recipeId");
		String userId = request.getParameter("userId");
		
		log.debug("Delete Recipe : {}", recipeId);
		
		HttpSession session = request.getSession();	
		
		if ((UserSessionUtils.isLoginUser("admin", session) && !userId.equals("admin")) 
				|| 												// 또는 
			(!UserSessionUtils.isLoginUser("admin", session) &&  UserSessionUtils.isLoginUser(userId, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
			
			recipeDAO.deleteRecipe(recipeId);
			if (UserSessionUtils.isLoginUser("admin", session))
				return "redirect:/recipe/findByRecipe2";
			else
				return "redirect:/refrigerator/view";
			
		}
		return "/recipe/findByRecipe1";
	}
}
