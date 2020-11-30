package controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.review.CreateReviewController;
import controller.review.DeleteReviewController;
import controller.review.UpdateReviewController;
import controller.user.*;
import controller.refrigerator.*;
import controller.ingredient.FindIngredientController;
import controller.recipe.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 占쏙옙 占쏙옙청 uri占쏙옙 占쏙옙占쏙옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙占쏙옙 HashMap 占쏙옙占쏙옙
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// user
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/update/form", new UpdateUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/test", new ForwardController("/user/userTestPage.jsp"));
        // Review
        mappings.put("/review/create", new CreateReviewController());
        mappings.put("/review/delete", new DeleteReviewController());
        mappings.put("/review/update", new UpdateReviewController());
        //refrigerator
        mappings.put("/refrigerator/view", new RefrigeratorController());
        mappings.put("/refrigerator/select", new ForwardController("/refrigerator/addIngredients.jsp"));
        mappings.put("/refrigerator/add", new AddUserIngredientController());
        mappings.put("/refrigerator/delete", new DeleteIngredientController());
        // Recipe
        mappings.put("/recipe/createForm", new ForwardController("/recipe/writeRecipe.jsp"));
        mappings.put("/recipe/create", new CreateRecipeController());
        mappings.put("/recipe/update", new UpdateRecipeController());
        mappings.put("/recipe/delete", new DeleteRecipeController());
        mappings.put("/recipe/view", new ViewRecipeController());
        mappings.put("/recipe/myList", new ViewMyRecipeController());
        mappings.put("/recipe/findByRcpName", new FindByRcpNameController());
        mappings.put("/recipe/findByIng/form", new ForwardController("/recipe/findByIngredient.jsp"));
        mappings.put("/recipe/findByIng", new FindByIngListController());
        //ingredient
       
        mappings.put("/ingredient/find", new FindIngredientController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 占쌍억옙占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 찾占쏙옙 占쏙옙환
        return mappings.get(uri);
    }
}
