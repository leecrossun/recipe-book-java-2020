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

public class UpdateRecipeController implements Controller{
	
	private RecipeDAO recipeDAO = new RecipeDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String recipeId = request.getParameter("recipeId");
		   
		if (request.getMethod().equals("GET")) {

			Recipe recipe = recipeDAO.FindRecipeById(recipeId);
			List<RecipeIngredient> rcpIng = recipeDAO.FindRcpIngById(recipeId);
			List<RecipeStep> rcpStep = recipeDAO.FindRcpStepById(recipeId);
			request.setAttribute("recipe", recipe);
			request.setAttribute("rcpIng", rcpIng);
			request.setAttribute("rcpStep", rcpStep);
			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(recipe.getUserId(), session)||
				UserSessionUtils.isLoginUser("admin", session)) {
				return "recipe/writeRecipe.jsp"; // 등록 폼과 수정 폼이 동일하다면 writeRecipe.jsp 창을 띄워줌
			}
			
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인이 작성한 글은 수정할 수 없습니다"));            
			return "/recipe/viewRecipe.jsp";
		}
		
		// POST request 
		Recipe recipe = new Recipe(
				recipeId,
				request.getParameter("recipeName"),
				request.getParameter("userId"),
				request.getParameter("summary"),
				request.getParameter("nation"),
				request.getParameter("difficulty"),
				request.getParameter("image"),
				Integer.parseInt(request.getParameter("report")));
		recipeDAO.updateRecipe(recipe);
		
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
		recipeDAO.updateRecipeIngredient(rcpIngList);
		
		List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
		String[] stepNum = request.getParameterValues("stepNum");
		String[] content = request.getParameterValues("content");
		size = stepNum.length;
		for (int i = 0; i < size; i++) {
			RecipeStep rcpStep = new RecipeStep(recipeId, Integer.parseInt(stepNum[i]), content[i]);
			rcpStepList.add(rcpStep);
		}
		recipeDAO.updateRecipeStep(rcpStepList);
		
		return "redirect:/recipe/view";

	}
}
