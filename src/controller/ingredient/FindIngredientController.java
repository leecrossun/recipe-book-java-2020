package controller.ingredient;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import persistence.dao.IngredientDAO;
import service.dto.Ingredient;

public class FindIngredientController implements Controller {
	
	private IngredientDAO ingredientDAO;
	
	public FindIngredientController() {
		try {
			ingredientDAO = new IngredientDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String ingName = request.getParameter("keyword");
		
		try {
			List<Ingredient> ingredientList = ingredientDAO.findIngredient(ingName);
			request.setAttribute("ingredientList", ingredientList);
			request.setAttribute("keyword", ingName);
		} catch (Exception e) {
			
		}
		return "/ingredient/find.jsp";
		}
}
