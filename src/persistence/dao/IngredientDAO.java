package persistence.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import service.dto.Ingredient;

public class IngredientDAO {
private static JDBCUtil jdbcUtil = null;
	
	public IngredientDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	//재료 추가 전 재료명으로 재료 검색 
	public List<Ingredient> findIngredient(String ingName) {
		String sql = "SELECT * FROM INGREDIENT WHERE INGREDIENTNAME LIKE '%' || ? || '%'";
		Object[] param = new Object[] { ingName };
		List<Ingredient> list = null;
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			list = new ArrayList<Ingredient>();

			while (rs.next()) {
				Ingredient ingredient = new Ingredient(rs.getString("INGREDIENTID"), rs.getString("INGREDIENTNAME"));
				list.add(ingredient);
			}
			
			if (list.isEmpty())
				System.out.println("finding Ingredients failed");
			else
				System.out.println("finding Ingredients success");
			
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public String findIngredientId(String ingredientName) {
		String sql = "SELECT INGREDIENTID FROM INGREDIENT WHERE INGREDIENTNAME =?";
		Object[] param = new Object[] { ingredientName };
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();

			if (rs.next()) {
				String ingId = rs.getString("INGREDIENTID");
				return ingId;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
