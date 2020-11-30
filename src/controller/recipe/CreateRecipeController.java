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
import persistence.dao.IngredientDAO;
import persistence.dao.RecipeDAO;
import service.dto.Ingredient;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;
//파일 업로드를 위한 API를 사용하기 위해...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
//파일 용량 초과에 대한 Exception 클래스를 FileUploadBase 클래스의 Inner 클래스로 처리
import org.apache.commons.fileupload.servlet.*;

public class CreateRecipeController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);
	private RecipeDAO recipeDAO = new RecipeDAO();
	private IngredientDAO ingredientDAO = new IngredientDAO();
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
		
		// https://jeanette.tistory.com/62 (writeRecipeJSP 수정필요. 참고)
		//ingredient를 추가할 때 ingredientId를 가져오는 방법에 대해 고민을 해보아야 할 것 같습니다. 
		
		List<RecipeIngredient> rcpIngList = new ArrayList<RecipeIngredient>();
		
		String[] ingName = request.getParameterValues("ingName");
		String[] ingId = new String[ingName.length];
		String[] amount = request.getParameterValues("amount");
		String[] unit = request.getParameterValues("unit");
		
		for(int i = 0; i < ingId.length; i++) {
			List<Ingredient> ingList = ingredientDAO.findIngredient(ingName[i]);
			Ingredient ingredient = ingList.get(0);
			ingId[i] = ingredient.getIngredientId();
		}

		
		for(int i = 0; i < ingId.length; i++) {
			RecipeIngredient rcpIng = new RecipeIngredient(null, ingId[i], ingName[i], Double.parseDouble(amount[i]), unit[i]);
			rcpIngList.add(rcpIng);
		}
		recipe.setIngList(rcpIngList);
		
		List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
		String[] stepList = request.getParameterValues("stepList");
		
		//recipeId를 아직 모르기 때문에 생성자에서 recipeId를 뺐습니다. 
		for (int i = 0; i < stepList.length; i++) {
			RecipeStep rcpStep = new RecipeStep(i+1, stepList[i]);
			rcpStepList.add(rcpStep);
		}
		
		recipe.setStepList(rcpStepList);
		//여기서 recipeingredient, recipetable에 저장됩니다.
		
		log.debug("Create recipe : {}", recipe.toString());
		
		int generatedKey = recipeDAO.insertRecipe(recipe);
		
		try {
			//int generatedKey = recipeDAO.insertRecipe(recipe);
			// recipe.setRecipeId(String.valueOf(generatedKey));
			//recipeDAO.insertRecipeStep(String.valueOf(generatedKey), rcpStepList);
		
			log.debug("Create recipe : {}", recipe.toString());
/*			request.setAttribute("recipe", recipe);*/
			request.setAttribute("recipeId", String.valueOf(generatedKey));
			/*request.setAttribute("rcpStep", rcpStepList);
			request.setAttribute("rcpIng", rcpIngList); //
			request.setAttribute("review", null);*/
			
			return "/recipe/view";
		}catch(Exception e){
			//return "/recipe/writeRecipe.jsp";
			return "/refrigerator/view";
		}
	}
}


