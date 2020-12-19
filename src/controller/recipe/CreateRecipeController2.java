package controller.recipe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.IngredientDAO;
import persistence.dao.RecipeDAO;
import service.dto.Ingredient;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;

public class CreateRecipeController2 implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateRecipeController2.class);
	private RecipeDAO recipeDAO = new RecipeDAO();
	private IngredientDAO ingredientDAO = new IngredientDAO();

	public CreateRecipeController2() {
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

		Recipe recipe = new Recipe();

		recipe.setUserId(userId);
		String filename = "";

		boolean check = ServletFileUpload.isMultipartContent(request);

		if (check) {// 파일 전송이 포함된 상태가 맞다면
			String path = session.getServletContext().getRealPath("/upload");
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdir();
			
			try {
				MultipartRequest multi = new MultipartRequest(request, path, 1024 * 1024 * 10, "utf-8",
						new DefaultFileRenamePolicy());

				recipe.setRecipeName(multi.getParameter("recipeName"));
				recipe.setSummary(multi.getParameter("summary"));
				recipe.setNation(multi.getParameter("nation"));
				recipe.setDifficulty(multi.getParameter("difficulty"));
				recipe.setImage(dir + "\\" + multi.getFilesystemName("image"));

				List<RecipeIngredient> rcpIngList = new ArrayList<RecipeIngredient>();
				String[] ingName = multi.getParameterValues("ingName");
				String[] ingId = new String[ingName.length];
				String[] amount = multi.getParameterValues("amount");
				String[] unit = multi.getParameterValues("unit");
				
				log.debug("Create recipe : {}", recipe.toString());
				
				for(int i = 0; i < ingId.length; i++) {
					List<Ingredient> ingList = ingredientDAO.findIngredient(ingName[i]);
					Ingredient ingredient = ingList.get(0);
					ingId[i] = ingredient.getIngredientId();
				}
				
				for (int i = 0; i < ingId.length; i++) {
					RecipeIngredient rcpIng = new RecipeIngredient(null, ingId[i], ingName[i],
							Double.parseDouble(amount[i]), unit[i]);
					rcpIngList.add(rcpIng);
				}
				recipe.setIngList(rcpIngList);

				List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
				String[] stepList = multi.getParameterValues("stepList");

				for (int i = 0; i < stepList.length; i++) {
					RecipeStep rcpStep = new RecipeStep(i + 1, stepList[i]);
					rcpStepList.add(rcpStep);
				}

				recipe.setStepList(rcpStepList);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed");
				return "/refrigerator/view";
			}
		}
		
		try {
			int generatedKey = recipeDAO.insertRecipe(recipe);
			request.setAttribute("recipeId", String.valueOf(generatedKey));
			return "/recipe/view";
		} catch (Exception e) {
			System.out.println("recipe create failed");
			return "/refrigerator/view";
		}
		
	}
}
