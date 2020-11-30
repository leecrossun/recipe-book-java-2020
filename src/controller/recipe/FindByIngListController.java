package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.RecipeDAO;
import persistence.dao.RefrigeratorDAO;
import service.dto.Recipe;
import service.dto.UserIngredient;

public class FindByIngListController implements Controller{
	
	private RecipeDAO recipeDAO;
	private RefrigeratorDAO refrigeratorDAO;
	
	public FindByIngListController() {
		try {
			recipeDAO = new RecipeDAO();
			refrigeratorDAO = new RefrigeratorDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		String userId = UserSessionUtils.getLoginUserId(session);
		
		List<UserIngredient> userIngredient = refrigeratorDAO.getIngredientList(userId);
		
		String[] ingIds = new String[userIngredient.size()];
		
		for(int i = 0; i < userIngredient.size(); i++) {
			ingIds[i] = userIngredient.get(i).getIngredientId();
		}
		
		List<Recipe> recipeList = recipeDAO.getRecipeListByIng(ingIds);
		
		request.setAttribute("SearchedRcps", recipeList);
		request.setAttribute("userIngredients", userIngredient);
		
		return "/recipe/findByIngredient.jsp";
	}
	
	
}
