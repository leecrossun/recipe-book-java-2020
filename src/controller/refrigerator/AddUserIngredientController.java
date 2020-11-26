package controller.refrigerator;

import java.sql.Date;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import persistence.dao.IngredientDAO;
import persistence.dao.RefrigeratorDAO;
import service.dto.Ingredient;
import service.dto.UserIngredient;

public class AddUserIngredientController implements Controller{
	
	private RefrigeratorDAO refrigeratorDAO;
	
	private IngredientDAO ingredientDAO;
	
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
		HttpSession session = request.getSession();	
		
		String userId = UserSessionUtils.getLoginUserId(session);
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date expiredDate = (Date)sdFormat.parse(request.getParameter("expriedDate"));
		
		String ingName = request.getParameter("selectName");
		List<Ingredient> ingredientList = ingredientDAO.findIngredient(ingName);
		Ingredient ingredient = ingredientList.get(0);
		
	    UserIngredient uIng = new UserIngredient(
	    		userId,
				ingredient.getIngredientName(),
				ingredient.getIngredientId(),
				Integer.parseInt(request.getParameter("amount")),
				request.getParameter("unit"),
				expiredDate);
	    
	    refrigeratorDAO.addUserIngredient(uIng);
	    
		return "redirect:/refrigerator/view";
	}
}
