package controller.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.RegisterUserController;
import controller.user.UserSessionUtils;
import persistence.dao.RecipeDAO;
import service.dto.Recipe;
import service.dto.RecipeStep;

public class CreateRecipeController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);
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
		Recipe recipe = new Recipe(
				request.getParameter("recipeName"),
				userId,
				request.getParameter("summary"),
				request.getParameter("nation"),
				request.getParameter("difficulty"),
				request.getParameter(request.getParameter("image")), 0); //처음 레시피를 삽입할때는 report를 0을 기본값으로 교체했습니다.  
		
		log.debug("Create User : {}", recipe.toString());
		// https://jeanette.tistory.com/62 (writeRecipeJSP 수정필요. 참고)
		//ingredient를 추가할 때 ingredientId를 가져오는 방법에 대해 고민을 해보아야 할 것 같습니다. 
		
//		List<RecipeIngredient> rcpIngList = new ArrayList<RecipeIngredient>();
//		String[] ingId = request.getParameterValues("ingredientId");
//		String[] ingName = request.getParameterValues("IngredientName");
//		String[] amount = request.getParameterValues("amoount");
//		String[] unit = request.getParameterValues("unit");
//		int size = ingId.length;
		
//		for(int i = 0; i < size; i++) {
//			RecipeIngredient rcpIng = new RecipeIngredient(recipeId, ingId[i], ingName[i], Integer.parseInt(amount[i]), unit[i]);
//			rcpIngList.add(rcpIng);
//		}
//		recipe.setIngList(rcpIngList);
		
		List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
		String[] stepList = request.getParameterValues("stepList");
		int size = stepList.length;
		
		//recipeId를 아직 모르기 때문에 생성자에서 recipeId를 뺐습니다. 
		for (int i = 0; i < size; i++) {
			RecipeStep rcpStep = new RecipeStep(i+1, stepList[i]);
			rcpStepList.add(rcpStep);
		}
		recipe.setStepList(rcpStepList);
		recipeDAO.insertRecipe(recipe);
		log.debug("Create User : {}", recipe.toString());
		request.setAttribute("recipe", recipe);
		
		return "/recipe/viewRecipe.jsp";
	}
}


