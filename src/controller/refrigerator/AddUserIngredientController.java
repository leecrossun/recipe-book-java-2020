package controller.refrigerator;

import java.util.Date;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import controller.user.UserSessionUtils;
import persistence.dao.IngredientDAO;
import persistence.dao.RefrigeratorDAO;
import service.dto.Ingredient;
import service.dto.UserIngredient;

public class AddUserIngredientController implements Controller{
	
	private RefrigeratorDAO refrigeratorDAO;
	private IngredientDAO ingredientDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(AddUserIngredientController.class);
	
	public AddUserIngredientController() {
		try {
			refrigeratorDAO = new RefrigeratorDAO();
			ingredientDAO = new IngredientDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		if(!UserSessionUtils.hasLogined(request.getSession())){
			return "redirect:/user/login/form";
		}
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
		String exDate = request.getParameter("expiredDate");
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date expiredDate = sdFormat.parse(exDate);
		
		String ingName = request.getParameter("ingredientName");
		List<Ingredient> ingredientList = ingredientDAO.findIngredient(ingName);
		Ingredient ingredient = ingredientList.get(0);
		
		logger.debug("userId : {}, ingredientName : {}, amount : {}, expiredDate : {}", 
				userId, ingredient.getIngredientName(), request.getParameter("amount"), expiredDate);
		
	    UserIngredient uIng = new UserIngredient(
	    		userId,
				ingredient.getIngredientId(),
				Integer.parseInt(request.getParameter("amount")),
				request.getParameter("unit"),
				exDate);
	    
	    refrigeratorDAO.addUserIngredient(uIng);
	    
	    request.setAttribute("userId", userId);
	    
		return "redirect:/refrigerator/view";
	}
}
