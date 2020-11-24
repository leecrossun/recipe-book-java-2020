package controller.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.RecipeDAO;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;

public class CreateRecipeController implements Controller{

	private RecipeDAO recipeDAO = new RecipeDAO();
	
	public CreateRecipeController() {
		try {
			recipeDAO = new RecipeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();	
		String userId = UserSessionUtils.getLoginUserId(session);
		
		// CreateRecipe
		String recipeId = request.getParameter("recipeId");
		Recipe recipe = new Recipe(
				recipeId, 
				request.getParameter("recipeName"),
				userId,
				request.getParameter("summary"),
				request.getParameter("nation"),
				request.getParameter("difficulty"),
				request.getParameter("image"),
				Integer.parseInt(request.getParameter("report")));
		
		// https://jeanette.tistory.com/62 (writeRecipeJSP 수정필요. 참고)
		List<RecipeIngredient> rcpIngList = new ArrayList<RecipeIngredient>();
		String[] ingId = request.getParameterValues("ingredientId");
		String[] ingName = request.getParameterValues("IngredientName");
		String[] amount = request.getParameterValues("amoount");
		String[] unit = request.getParameterValues("unit");
		int size = ingId.length;
		for(int i = 0; i < size; i++) {
			RecipeIngredient rcpIng = new RecipeIngredient(recipeId, ingId[i], ingName[i], Integer.parseInt(amount[i]), unit[i]);
			rcpIngList.add(rcpIng);
		}
		recipe.setIngList(rcpIngList);
		
		List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
		String[] stepNum = request.getParameterValues("stepNum");
		String[] content = request.getParameterValues("content");
		size = stepNum.length;
		for (int i = 0; i < size; i++) {
			RecipeStep rcpStep = new RecipeStep(recipeId, Integer.parseInt(stepNum[i]), content[i]);
			rcpStepList.add(rcpStep);
		}
		recipe.setStepList(rcpStepList);
		recipeDAO.insertRecipe(recipe);
		
		request.setAttribute("recipe", recipe);
		
		return "/recipe/viewRecipe.jsp";
	}
}


