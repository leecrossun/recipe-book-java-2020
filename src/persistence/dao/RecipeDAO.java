package persistence.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;

public class RecipeDAO {

	private static JDBCUtil jdbcUtil = null;

	public RecipeDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int insertRecipe(Recipe rcp) {

		int result = 0;
		int generatedKey = 0;
		
		// insert Recipe
		String sql = "INSERT INTO RECIPE (recipeId, recipeName, summary, nation, difficulty, userId, image, report, published) "
				+ "VALUES (recipeId_seq.nextval, ?, ?, ?, ?, ?, ?, ?,SYSDATE )";
		Object[] param = new Object[] { rcp.getRecipeName(), rcp.getSummary(), rcp.getNation(),
				rcp.getDifficulty(), rcp.getUserId(), rcp.getImage(), 0};
		jdbcUtil.setSqlAndParameters(sql, param);
		String key[] = {"recipeId"};
		
		try {
			jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(1);
				rcp.setRecipeId(String.valueOf(generatedKey));
				System.out.println("insert recipe success");
				
				// insert RecipeIngredient
				sql = "INSERT INTO RECIPE_INGREDIENT(recipeId, ingredientId, amount, unit) "
						+ "VALUES (?, ?, ?, ?)";
				List<RecipeIngredient> rcpIngList = rcp.getIngList();
				for (int i = 0; i < rcpIngList.size(); i++) {
					param = new Object[] { String.valueOf(generatedKey), rcpIngList.get(i).getIngredientId(),
							 rcpIngList.get(i).getAmount(),
							rcpIngList.get(i).getUnit() };
					jdbcUtil.setSqlAndParameters(sql, param);
					result += jdbcUtil.executeUpdate();
				}
				if (result != rcpIngList.size())
					throw new Exception("레시피 재료 저장에 실패했습니다.");
				else
					System.out.println("insert recipeIng success");
				
				// insert RecipeStep
				sql = "INSERT INTO RECIPESTEP(recipeId, stepNum, content) " + "VALUES (?, ?, ?)";
				List<RecipeStep> rcpStepList = rcp.getStepList();
				for (int i = 0; i < rcpStepList.size(); i++) {
					param = new Object[] { String.valueOf(generatedKey), rcpStepList.get(i).getStepNum(),
							rcpStepList.get(i).getContent() };
					jdbcUtil.setSqlAndParameters(sql, param);
					result = jdbcUtil.executeUpdate();
				}
				if (result != rcpStepList.size())
					throw new Exception("레시피 순서 저장에 실패했습니다.");
				else
					System.out.println("insert recipeStep success");

			}
			return generatedKey;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return generatedKey;
	}

	public void deleteRecipe (String rcpId) {
		
		String sql = "DELETE FROM RECIPESTEP WHERE recipeID = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {rcpId});
		
		try {
			int result = jdbcUtil.executeUpdate();
			if (result > 0)	
				System.out.println("delete recipeStep success.");
			else	
				throw new Exception("레시피 순서 삭제에 실패했습니다.");
			
			sql = "DELETE FROM RECIPE_INGREDIENT WHERE recipeId = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {rcpId});
			result = jdbcUtil.executeUpdate();
			if (result > 0)	
				System.out.println("delete recipeIngredient success.");
			else	
				throw new Exception("레시피 재료 삭제에 실패했습니다.");
			
			sql = "DELETE FROM REVIEW WHERE recipeId = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {rcpId});
			result = jdbcUtil.executeUpdate();
			System.out.println("delete review success.");
			
			sql = "DELETE FROM RECIPE WHERE recipeId = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {rcpId});
			result = jdbcUtil.executeUpdate();
			if (result == 1)
				System.out.println("delete recipe success.");
			else 
				throw new Exception("레시피 삭제에 실패했습니다.");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			jdbcUtil.rollback();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
	}

	public List<Recipe> getMyRecipeList(String userId){
		List<Recipe> myRecipeList = null;
		String sql = "SELECT recipeId, recipeName, userId, summary, nation, difficulty, image, report " + "FROM RECIPE "
				+ "WHERE userId = ? ";
		Object[] param = {userId};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			myRecipeList = new ArrayList<Recipe>();

			while(rs.next()) {
				Recipe rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setUserId(rs.getString("userId"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setNation(rs.getString("nation"));
				rcp.setDifficulty(rs.getString("difficulty"));
				rcp.setImage(rs.getString("image"));
				rcp.setReport(rs.getInt("report"));
				myRecipeList.add(rcp);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return myRecipeList;
	}
	
	public List<Recipe> getRecipeListByName(String recipeName) {
		String sql = "SELECT recipeId, recipeName, summary, image " + "FROM RECIPE "
				+ "WHERE recipeName LIKE '%' || ? || '%'";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {  "%"+recipeName+"%" });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Recipe> rcpList = new ArrayList<Recipe>();
			while (rs.next()) {
				Recipe rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setImage(rs.getString("image"));
				rcpList.add(rcp);
			}
			if (rcpList.isEmpty())
				System.out.println("empty refrigerator");
			return rcpList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<Recipe >getRecipeListByIng(String[] ingIds) {
		String sql = "SELECT recipeId, recipeName, summary, image, COUNT(recipeId) AS cnt "
				+ "FROM ( SELECT r.recipeId, i.ingredientId, r.recipeName, r.summary, r.image " 
				+ "FROM recipe r, recipe_ingredient ri, ingredient i "
				+ "WHERE r.recipeId = ri.recipeId AND ri.ingredientId = i.ingredientId "
				+ "AND i.ingredientId IN (? ";
		
		Object[] params = new Object[ingIds.length];
		for (int i = 0; i < ingIds.length; i++) {
			params[i] = ingIds[i];
		}
		
		for (int i = 0; i < ingIds.length-1; i++) {
			sql += ", ? ";
		}
		
		sql += ")) GROUP BY recipeId, recipeName, summary, image ORDER BY cnt DESC ";
		
		jdbcUtil.setSqlAndParameters(sql, params);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Recipe> rcpList = new ArrayList<Recipe>();
			
			while (rs.next()) {
				Recipe rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setImage(rs.getString("image"));
				rcpList.add(rcp);
			}
			return rcpList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public Recipe findRecipeById(String recipeId) {
		String sql = "SELECT recipeId, recipeName, userId, summary, nation, difficulty, image, report " + "FROM RECIPE "
				+ "WHERE recipeId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});

		Recipe rcp = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setUserId(rs.getString("userId"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setNation(rs.getString("nation"));
				rcp.setDifficulty(rs.getString("difficulty"));
				rcp.setImage(rs.getString("image"));
				rcp.setReport(rs.getInt("report"));
			}
			rcp.setIngList(findRcpIngById(recipeId));
			rcp.setStepList(findRcpStepById(recipeId));
			return rcp;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<RecipeIngredient> findRcpIngById (String recipeId) {
		String sql = "SELECT R1.recipeId, R2.ingredientId, ingredientName, amount, unit " + "FROM RECIPE R1, RECIPE_INGREDIENT R2, INGREDIENT I " 
					+ "WHERE R2.ingredientId=I.ingredientId and R1.recipeId=R2.recipeId and R1.recipeId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<RecipeIngredient> rcpIngList = new ArrayList<RecipeIngredient>();
			while (rs.next()) {
				RecipeIngredient rcpIng = new RecipeIngredient();
				rcpIng.setIngredientName(rs.getString("ingredientName"));
				rcpIng.setAmount(rs.getDouble("amount"));
				rcpIng.setUnit(rs.getString("unit"));
				rcpIngList.add(rcpIng);
			}
			return rcpIngList;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("failed");
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<RecipeStep> findRcpStepById (String recipeId) {
		String sql = "SELECT stepNum, content " + "FROM RECIPESTEP " + "WHERE recipeId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});
		RecipeStep rcpStep = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
			while (rs.next()) {
				rcpStep = new RecipeStep();
				rcpStep.setStepNum(rs.getInt("stepNum"));
				rcpStep.setContent(rs.getString("content"));
				rcpStepList.add(rcpStep);
			}
			return rcpStepList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
