package controller.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import persistence.dao.RecipeDAO;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;

public class CreateRecipeController implements Controller{

	private RecipeDAO recipeDAO = new RecipeDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// CreateRecipe
		String recipeId = request.getParameter("recipeId");
		Recipe recipe = new Recipe(
				recipeId, 
				request.getParameter("recipeName"),
				request.getParameter("userId"),
				request.getParameter("summary"),
				request.getParameter("nation"),
				request.getParameter("difficulty"),
				request.getParameter("image"),
				Integer.parseInt(request.getParameter("report")));
		
		// https://jeanette.tistory.com/62 (writeRecipeJSP 수정필요. 참고)
		
		// CreateRecipeIngredient
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
		
		// CreateRecipeStep
		List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
		String[] stepNum = request.getParameterValues("stepNum");
		String[] content = request.getParameterValues("content");
		size = stepNum.length;
		for (int i = 0; i < size; i++) {
			RecipeStep rcpStep = new RecipeStep(recipeId, Integer.parseInt(stepNum[i]), content[i]);
			rcpStepList.add(rcpStep);
		}
		
		// rollback 문제 발생. 어떻게 해결?
		// rcpIngList와 rcpStepList를 DTO에 넣어서 insertRecipe 메소드 안에서 다른 메소드도 동시에 돌리면
		// 해결 가능할 것 같으나 DTO에 담기는 정보가 너무 많다는 단점이 있음.
		recipeDAO.insertRecipe(recipe);
		recipeDAO.insertRecipeIngredient(rcpIngList);
		recipeDAO.insertRecipeStep(rcpStepList);
		
		request.setAttribute("recipe", recipe);
		request.setAttribute("rcpIngList", rcpIngList);
		request.setAttribute("rcpStepList", rcpStepList);
		
		return "/recipe/viewRecipe.jsp";
	}
}


