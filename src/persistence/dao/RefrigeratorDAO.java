package persistence.dao;

import java.sql.*;
import java.util.*;

import service.dto.Ingredient;
import service.dto.Recipe;
import service.dto.Review;
import service.dto.UserIngredient;

public class RefrigeratorDAO {
	private static JDBCUtil jdbcUtil = null;
	
	public RefrigeratorDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	//냉장고에 저장한 재료 표시
	public List<UserIngredient> getIngredientList(String userId) {
		String sql = "SELECT INGREDIENTNAME, AMOUNT, UNIT, EXPIRATION "
				+ "FROM INGREDIENT i JOIN USER_INGREDIENT ug USING (INGREDIENTID) "
				+ "WHERE USERID = ? ";
		Object[] param = new Object[] { userId };
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<UserIngredient> list = new ArrayList<UserIngredient>();
			
			while (rs.next()) {
				UserIngredient uIng = new UserIngredient(rs.getString("INGREDIENTNAME"), rs.getInt("AMOUNT"),
						rs.getString("UNIT"), rs.getDate("EXPIRATION"));
				list.add(uIng);
			}
			if (list.isEmpty())
				System.out.println("empty refrigerator");
			return list;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//재료 추가 전 재료명으로 재료 검색 
	public List<Ingredient> findIngredient(String ingName) {
		String sql = "SELECT * FROM INGREDIENT WHERE WHERE INGREDIENTNAME LIKE ? ";
		Object[] param = new Object[] { "%" + ingName + "%" };
		List<Ingredient> list = null;
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			list = new ArrayList<Ingredient>();

			while (rs.next()) {
				
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
		return list;
	}
	
	//냉장고 재료 추가
	public void addUserIngredient(UserIngredient uIng) {
		String sql = "INSERT INTO USER_INGREDIENT VALUES (?, ?, ?, ?, ?)";
		Object[] param = new Object[] { uIng.getUserId(), uIng.getIngredientId(), 
				uIng.getAmount(), uIng.getUnit(), uIng.getExpireDate() };
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			if (result > 0)
				System.out.println("add ingredient success");
			else
				System.out.println("add ingredient failed");
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
	}
	
	//재료 유효기간 얼마 남았는지 계산
	public List<String> calRemainingTime(String userId) {
		String sql = "SELECT ROUND(expiration - SYSDATE) - 1 AS REMAINING " 
				+ "FROM USER_INGREDIENT "
				+ "WHERE USERID = ? ";
		Object[] param = new Object[] { userId };
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<String> list = new ArrayList<String>();
			
			while (rs.next()) {
				String rTime = rs.getString("REMAINING");
				list.add(rTime);
			}
			return list;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//냉장고 재료 삭제
	public void deleteUserIngredient(String userId, String ingId) {
		String sql = "DELETE FROM USER_INGREDIENT WHERE USERID = ? AND INGFREDIENTID = ? ";
		Object[] param = new Object[] { userId, ingId };
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			if (result > 0)
				System.out.println("delete ingredient success");
			else
				System.out.println("delete ingredient failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 諛섑솚
		}
	}
	
	//냉장고 재료 검색
	public List<UserIngredient> f (String userId, String ingName) {
		String sql = "SELECT INGREDIENTNAME, AMOUNT, UNIT, EXPIRATION "
				+ "FROM INGREDIENT i JOIN USER_INGREDIENT ug USING (INGREDIENTID) "
				+ "WHERE USERID = ? AND INGREDIENTNAME LIKE ? ";
		Object[] param = new Object[] { userId, "%"+ingName+"%" };
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<UserIngredient> list = new ArrayList<UserIngredient>();
			
			while (rs.next()) {
				UserIngredient uIng = new UserIngredient(rs.getString("INGREDIENTNAME"), rs.getInt("AMOUNT"),
						rs.getString("UNIT"), rs.getDate("EXPIRATION"));
				list.add(uIng);
			}
			if (list.isEmpty())
				System.out.println("not found ingredient");
			return list;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//즐겨찾기 레시피 표시
	public List<Recipe> getFavoriteRecipetList(String userId) {
		String sql = "SELECT RECIPENAME, SUMMARY "
				+ "FROM FAVORITE f JOIN RECIPE r USING (RECIPEID) "
				+ "WHERE f.USERID = ? ";
		Object[] param = new Object[] { userId };
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Recipe> list = new ArrayList<Recipe>();
			
			while (rs.next()) {
				Recipe recipe = new Recipe(rs.getString("RECIPENAME"), rs.getString("SUMMARY"));
				list.add(recipe);
			}
			if (list.isEmpty())
				System.out.println("empty favorite recipe");
			return list;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	//즐겨찾기 레시피 삭제
	public void deletFavoriteRecipe(String userId, String recipeId) {
		String sql = "DELETE FROM FAVORITE WHERE USERID = ? AND RECIPEID = ? ";
		Object[] param = new Object[] { userId, recipeId };
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			if (result > 0)
				System.out.println("delete favorite recipe success");
			else
				System.out.println("delete favorite recipe failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 諛섑솚
		}
	}
	
	// 내가 작성한 레시피 표시
	public List<Recipe> getMyRecipetList(String userId) {
		String sql = "SELECT RECIPENAME, SUMMARY "
				+ "FROM RECIPE "
				+ "WHERE USERID = ? ";
		Object[] param = new Object[] { userId };
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Recipe> list = new ArrayList<Recipe>();
			
			while (rs.next()) {
				Recipe recipe = new Recipe(rs.getString("RECIPENAME"), rs.getString("SUMMARY"));
				list.add(recipe);
			}
			if (list.isEmpty())
				System.out.println("empty my recipe");
			return list;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
