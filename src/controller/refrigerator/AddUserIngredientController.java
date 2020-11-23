package controller.refrigerator;

import java.sql.Date;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import persistence.dao.RefrigeratorDAO;
import service.dto.UserIngredient;

public class AddUserIngredientController implements Controller{
	
	private RefrigeratorDAO refrigeratorDAO = new RefrigeratorDAO();
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date expiredDate = (Date)sdFormat.parse(request.getParameter("expriedDate"));
		
	    UserIngredient uIng = new UserIngredient(
				request.getParameter("ingredientName"),
				Integer.parseInt(request.getParameter("amount")),
				request.getParameter("unit"),
				expiredDate);
	    
	    refrigeratorDAO.addUserIngredient(uIng);
	    
		return "redirect:/refrigerator/view";
	}
}
